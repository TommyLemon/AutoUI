<h1 align="center" style="text-align:center;">
  AutoUI
</h1>
 
<p align="center">📱 AI 零代码快准稳 UI 自动化测试平台 🚀</p>
<p align="center">3 像素内自动精准定位，2 毫秒内自动精准等待，自动断言 UI 和数据、分配前/后端 bug，一键导出场景接口用例</p>
<p align="center" >
  <a href="https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Admin"><img src="https://img.shields.io/badge/Admin-Java1.8%2B-brightgreen.svg?style=flat"></a>
  <a href="https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Android"><img src="https://img.shields.io/badge/App-Android26%2B-brightgreen.svg?style=flat"></a>
  <a href="https://github.com/TommyLemon/AutoUI/tree/main/MySQL"><img src="https://img.shields.io/badge/MySQL-5.7%2B-brightgreen.svg?style=flat"></a>
</p>
<p align="center" >
  <a href="https://deepwiki.com/TommyLemon/AutoUI">English</a>
  <a href="https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Android#%E7%A4%BA%E4%BE%8B%E9%A1%B9%E7%9B%AE">录制回放</a>
  <a href="https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Android#%E5%BF%AB%E9%80%9F%E4%B8%8A%E6%89%8B">快速上手</a>
  <a href="http://apijson.cn/au">测试用例</a>
  <a href="https://deepwiki.com/TommyLemon/AutoUI">AI 问答</a>
</p>

<p align="center" >
<img width="1280" height="720" alt="AutoUI-testcases" src="https://github.com/user-attachments/assets/91daf1e8-943f-40ed-bc28-b05a359e9ff7" />
<img width="720" src="https://github.com/TommyLemon/UIGO/assets/5738175/49edbcdb-2bc6-4dfb-bd30-4f9aaafe4e25" />
<img width="1280" src="https://github.com/TommyLemon/UIGO/assets/5738175/54cf82d0-99b0-4085-ab89-d54ff95ef4c4" href="https://www.bilibili.com/video/BV1wA4m137ha" />
<img width="1280" src="https://github.com/TommyLemon/UIGO/assets/5738175/032de745-e49a-43f3-b368-84fdbd6a97d7" href="https://search.bilibili.com/all?keyword=UIGO" />
</p>

## AutoUI - 📱 AI 零代码快准稳 UI 自动化测试平台 🚀 
**3 像素内自动精准定位，2 毫秒内自动精准等待，录制回放快、准、稳，**<br />
**自动断言 UI 和数据、分析 bug 属于前端还是后端，一键导出场景接口用例！**<br />
适用于 一次录制到处回放、反复回归界面操作、App UI/功能 自动化测试、<br />
帮助开发快速复现和排查 bug、方便判断 bug 原因出在前端还是后端 等，<br />
**大量减少耗时费力又无聊的重复手工操作，大幅提高手工和自动化测试效率，**<br />
**强力杜绝 测试和开发、前端和后端 关于缺陷单踢皮球等各种低效扯皮内耗！**<br />

**用户包含腾讯，应微信团队邀请分享了 零代码测试工具与实践(API•单元•UI)**

### 支持功能
* **零代码 录制和回放 触屏、按键、键盘、数据 等**
* **支持 16:9 标准屏、19.5:9 全面屏等各种设备屏幕**
* **支持原生页面、内置 H5 网页、浏览器加载网页等**
* 支持 Android 真机、Studio/Genymotion 等模拟器
* **单双指点击、长按、滑动、缩放各种像素级精细操作**
* **自动精准等待、模拟 HTTP API 的请求和响应数据**
* **不同机型录制回放偏差基本仅在 3 像素、2 毫秒 内**
* **可从任意界面开始和停止录制、回放，绕过登录问题**
* 可自动对关键步骤截屏，方便对比回放与录制差异
* **可自动和手动选择 View 及触摸区域、贴靠方式等**
* 可保存录制步骤相关数据到后端数据库及从后端下载
* 可用管理端网页浏览检索用例和远程控制录制回放
* **可自动断言 UI 和数据、分析 bug 属于前端还是后端**
* **可一键导出场景接口用例，自动串联接口上下文依赖**

### 特点优势
相比各种 UI 录制回放/自动化测试 的 其它平台/工具/框架：<br /><br />
1.它们录制过程各种别扭难用反人类，甚至还需要开发/维护用例脚本、每个用例都写一大堆代码频繁部署等；<br />
**AutoUI 不需要写任何代码，录制几乎是按和人正常操作完全一样的方式，操作简单易用，录制回放快、准、稳！**<br />
<br />
2.它们很难兼容各种不同宽高比分辨率屏幕，720P, 1080P 等 16:9 屏幕录制最多只能较好地在 16:9 屏幕回放，<br />即便手写代码或图像比对等也很难在列表项 View id/图标 重复控件精准定位，经常点错位置导致大量回放失败；<br />
**AutoUI 则能很好地支持 16:9, 19.5:9 等各种不同屏幕录制，然后在 720P, 1080P, 2K, 1080X2340, 1440X3200 <br />等各种 不同机型、不同系统、不同屏幕 基本都能很好地精准回放，偏差基本仅在相当于一根头发丝的 3 像素内！**<br />
<br />
3.它们要到处人为设置/调整操作步骤等待时间，还总是要么等太久、要么还没返回就过早执行下一步导致出错，<br />因为几乎无法保证网络请求在精准时间内返回，所以总是界面没加载完就滑动、弹窗没显示就点了"确定"位置等；<br />
**AutoUI 则会自动精准等待 App 发送的各种 HTTP API 网络请求，偏差基本在 2 毫秒内，比眨眼一次还要快 50 倍，<br />像专业的测试工程师一样精准高效地等待数据和 UI 都加载好并执行 点击、长按、滑动、缩放 等每一步对应操作！**<br />
<br />

### 原理说明
被测项目不需要写任何用例脚本代码(逻辑代码、注解代码、配置代码等全都不要)，<br />
AutoUI 会自动录制 UI 触屏操作、虚拟+实体按键操作、HTTP API 网络请求与响应、<br />
Activity, Fragment, Dialog, PopupWindow 等各种组件(控件)元素的生命周期 等，<br />
回放时根据录制触摸点所在被分割球划分的 上、下、左、右、居中、等比 等区域<br />
以及 屏幕分辨率、状态栏高度、导航栏高度、键盘高度 等来自动计算出回放触摸点，<br />
再加上 id(如果有) 相同且距离最近的 View 区域来辅助微调，高度精准回放触屏操作！<br />
对 返回按键、键盘按键 甚至 输入框编辑过程的每个变化的字符 也都能精准无误地还原！<br />

<br />

### 示例项目
[AutoUI Android 简单测试 App](https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Android/UIAuto-Android)    直接 [下载](https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Android/releases/download/0.9.0/UIAuto.apk) （第一次可能失败，返回报错 JSON，一般重试一次就可以）<br />
[AutoUI Android 复杂客户端 App](https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Android/APIJSONApp)    直接 [下载](https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Android/releases/download/0.9.0/UIGO-release.apk) （第一次可能失败，返回报错 JSON，一般重试一次就可以）

#### 安装 App 必须授权 显示悬浮窗、读写文件存储 这两个权限
其它申请的权限也尽可能都勾选授权，如果不能提前授权，则在使用时弹出是否申请权限弹窗后再确认授权 <br />
https://github.com/TommyLemon/APIAuto/issues/61#issuecomment-1997047600

<br />

#### 早期零代码单机录制不同分辨率双机同时回放视频
https://www.bilibili.com/video/BV1CK4218788 <br />
<img width="1280" src="https://github.com/TommyLemon/UIGO/assets/5738175/3bb97384-72d9-4b45-ab2c-b0916291ef9f" href="https://www.bilibili.com/video/BV1CK4218788" />

#### 早期管理端网页工具零代码远程控制手机录制回放视频
https://www.bilibili.com/video/BV1wA4m137ha
<img width="1280" src="https://github.com/TommyLemon/UIGO/assets/5738175/e50d00a1-22e8-4908-9d88-579a178965f1" href="https://www.bilibili.com/video/BV1wA4m137ha" />

#### 早期仿微信朋友圈复杂 App 录制回放，弹窗、输入、网页、滑动、点击等
https://www.bilibili.com/video/BV1fH4y1E7gD
<img width="1280" src="https://github.com/TommyLemon/UIGO/assets/5738175/bed421fa-f1a9-47ea-a265-e34853b2d1c8" href="https://www.bilibili.com/video/BV1fH4y1E7gD" />

#### 零代码录制回放 H5 移动端网页输入、滑动、点击等操作
https://www.bilibili.com/video/BV1TK421C7y4
<img width="1280" src="https://github.com/TommyLemon/UIGO/assets/5738175/5c29bec6-2e21-4230-907c-f4ccb1faa4ef" href="https://www.bilibili.com/video/BV1TK421C7y4" />


#### Java 后端 Server
可先跳过，使用 http://apijson.cn:8080 或 http://apijson.cn:9090 代替 <br />

具体见：
https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Admin

#### 管理后台
可先跳过，http://apijson.cn/au 或 http://apijson.cn:8080/au/index.html 代替 <br />

具体见：
https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Admin

<br />

### 录制、回放用例
见以上 [录制用例](https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Android?tab=readme-ov-file#%E5%BD%95%E5%88%B6%E7%94%A8%E4%BE%8B)、[回放用例](https://github.com/TommyLemon/AutoUI/tree/main/AutoUI-Android?tab=readme-ov-file#%E5%9B%9E%E6%94%BE%E7%94%A8%E4%BE%8B) 的说明。

<br /><br />

### 常见问题
#### 1.apijson.cn 访问不了
托管服务地址改为 http://47.98.196.224:8080  <br />
https://github.com/TommyLemon/APIAuto/issues/13

<br />
  
更多常见问题 <br />
https://github.com/TommyLemon/APIAuto/issues

<br />


### 技术交流
##### 关于作者
[https://github.com/TommyLemon](https://github.com/TommyLemon)<br />
![](https://github.com/user-attachments/assets/cef2bd45-b20d-469e-8781-1d647cf0477f)

如果有什么问题或建议可以 [去 APIAuto 提 issue](https://github.com/TommyLemon/APIAuto/issues)，交流技术，分享经验。<br >
如果你解决了某些 bug，或者新增了一些功能，欢迎 [提 PR 贡献代码](https://github.com/Tencent/APIJSON/blob/master/CONTRIBUTING.md)，感激不尽。
<br />
<br />

### 我要赞赏
创作不易，右上角点亮 ⭐ Star 支持/收藏下本项目吧，谢谢 ^_^ <br />
https://github.com/TommyLemon/AutoUI
<br />
<br />
