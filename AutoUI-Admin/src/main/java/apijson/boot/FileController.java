/*Copyright ©2025 APIJSON(https://github.com/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.boot;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.*;

//import javax.annotation.PostConstruct;

import apijson.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson2.JSONObject;

import apijson.demo.DemoParser;

/**文件相关的控制器，包括上传、下载、浏览等
 * @author Lemon
 */
@Controller
public class FileController {

	public static final String HOME_DIR = System.getProperty("user.home");

	private static final String WINDOWS_DIR = HOME_DIR+ "\\upload\\";

	private static final String MAC_DIR = HOME_DIR + "/upload/";

	private static final String LINUX_DIR = HOME_DIR + "/upload/";

	private static String fileUploadRootDir = null;

    static {
		// 判断文件夹是否存在，不存在就创建
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Mac OS")) {
			// 苹果
			fileUploadRootDir = MAC_DIR;
		} else if (osName.startsWith("Windows")) {
			// windows
			fileUploadRootDir = WINDOWS_DIR;
		} else {
			// unix or linux
			fileUploadRootDir = LINUX_DIR;
		}

		File directories = new File(fileUploadRootDir);
		if (directories.exists()) {
			System.out.println("文件上传根目录已存在");
		} else { // 如果目录不存在就创建目录
			if (directories.mkdirs()) {
				System.out.println("创建多级目录成功");
			} else {
				System.out.println("创建多级目录失败");
			}
		}
	}

	public static final List<String> VIDEO_SUFFIXES = Arrays.asList("mp4");
	public static final List<String> IMG_SUFFIXES = Arrays.asList("jpg", "jpeg", "png");
	private static List<String> fileNames = null;

	@GetMapping("/files")
	@ResponseBody
	public JSONObject files() {
		File dir = new File(fileUploadRootDir);
		if (fileNames == null || fileNames.isEmpty()) {
			List<String> names = new ArrayList<>();
			File[] files = dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					String name = file == null ? null : file.getName();
					int ind = name == null ? -1 : name.lastIndexOf(".");
					String suffix = ind < 0 ? null : name.substring(ind + 1);
					boolean isImg = suffix != null;
					if (isImg) {
						names.add(name);
					}
					return isImg;
				}
			});

			fileNames = names;
		}

		JSONObject res = new JSONObject();
		res.put("data", fileNames);
		return new DemoParser().extendSuccessResult(res);
	}

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public JSONObject upload(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "url", required = false) String url
	) {
		try {
			byte[] bytes;
			String name;
			// 1 如果是文件上传
			if (file != null && !file.isEmpty()) {
				bytes = file.getBytes();
				name = file.getOriginalFilename();
			}
			// 2 如果是 URL 上传
			else if (url != null && url.startsWith("http")) {
				URL imageUrl = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
				conn.setConnectTimeout(10000);
				conn.setReadTimeout(10000);
				conn.setRequestProperty("User-Agent", "Mozilla/5.0");

				InputStream in = conn.getInputStream();
				bytes = in.readAllBytes();
				in.close();

				name = new File(imageUrl.getPath()).getName();
			}
			else {
				throw new RuntimeException("file or url required");
			}

			// 3 文件名处理
			name = (StringUtil.isEmpty(name)
					? DateFormat.getDateInstance().format(new Date())
					: name)
					.replaceAll("[^a-zA-Z0-9._-]", String.valueOf(Math.round(1100 * Math.random())));

			// 4 写入文件
			File convertFile = new File(fileUploadRootDir + name);
			FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
			fileOutputStream.write(bytes);
			fileOutputStream.close();

			if (fileNames != null && ! fileNames.isEmpty()) {
				fileNames.add(name);
			}

			JSONObject res = new JSONObject();
			res.put("path", "/download/" + name);
			res.put("size", bytes.length);
			return new DemoParser().extendSuccessResult(res);

		}
		catch (Exception e) {
			e.printStackTrace();
			return new DemoParser().newErrorResult(e);
		}
	}

	@GetMapping("/download/{fileName}")
	@ResponseBody
	public ResponseEntity<Object> download(@PathVariable(name = "fileName") String fileName) throws FileNotFoundException {

		File file = new File(fileUploadRootDir + fileName);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		String encodedFileName = fileName;
		try {
			encodedFileName = URLEncoder.encode(fileName, StringUtil.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Disposition", String.format("attachment;filename=\"%s;filename*=UTF_8''%s", fileName, encodedFileName));
		headers.add("Content-Disposition", String.format("attachment;filename=\"%s", fileName));
		headers.add("Cache-Control", "public, max-age=86400");
//		headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
//		headers.add("Pragma", "no-cache");
//		headers.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.length())
				.contentType(determineContentType(fileName))
				.body(resource);

		return responseEntity;
	}

	private MediaType determineContentType(String fileName) {
		String extension = getFileExtension(fileName).toLowerCase();
		switch (extension) {
			case "jpg":
			case "jpeg":
				return MediaType.IMAGE_JPEG;
			case "png":
				return MediaType.IMAGE_PNG;
			case "gif":
				return MediaType.IMAGE_GIF;
			case "pdf":
				return MediaType.APPLICATION_PDF;
			case "json":
				return MediaType.APPLICATION_JSON;
			case "xml":
				return MediaType.APPLICATION_XML;
			case "txt":
				return MediaType.TEXT_PLAIN;
			case "css":
				return MediaType.valueOf("text/css");
			case "js":
				return MediaType.valueOf("application/javascript");
			default:
				return MediaType.APPLICATION_OCTET_STREAM;
		}
	}

	private String getFileExtension(String path) {
		int ind = StringUtil.isEmpty(path) ? -1 : path.lastIndexOf("[.]");
		return ind < 0 ? "" : path.substring(ind + 1);
	}

}
