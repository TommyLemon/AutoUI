/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.demo;

import static apijson.framework.APIJSONConstant.ID;
import static apijson.framework.APIJSONConstant.PRIVACY_;
import static apijson.framework.APIJSONConstant.USER_;
import static apijson.framework.APIJSONConstant.USER_ID;

import apijson.RequestMethod;
import apijson.StringUtil;
import apijson.orm.AbstractSQLConfig;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import apijson.fastjson2.APIJSONSQLConfig;


/**SQL配置
 * TiDB 用法和 MySQL 一致
 * 具体见详细的说明文档 C.开发说明 C-1-1.修改数据库链接
 * https://github.com/Tencent/APIJSON/blob/master/%E8%AF%A6%E7%BB%86%E7%9A%84%E8%AF%B4%E6%98%8E%E6%96%87%E6%A1%A3.md#c-1-1%E4%BF%AE%E6%94%B9%E6%95%B0%E6%8D%AE%E5%BA%93%E9%93%BE%E6%8E%A5
 * @author Lemon
 */
public class DemoSQLConfig extends APIJSONSQLConfig<Long> {

	public DemoSQLConfig() {
		super();
	}
	public DemoSQLConfig(RequestMethod method, String table) {
		super(method, table);
	}

	static {
		DEFAULT_DATABASE = DATABASE_MYSQL;  //TODO 默认数据库类型，改成你自己的。TiDB, MariaDB, OceanBase 这类兼容 MySQL 的可当做 MySQL 使用
		DEFAULT_SCHEMA = "sys"; // "apijson";  //TODO 默认数据库名/模式，改成你自己的，默认情况是 MySQL: sys, PostgreSQL: sys, SQL Server: dbo, Manticore: Manticore, Oracle: , StarRocks: quickstart

		// 表名和数据库不一致的，需要配置映射关系。只使用 APIJSONORM 时才需要；
		// 这个项目用了 apijson-framework 且调用了 APIJSONApplication.init 则不需要
		// (间接调用 DemoVerifier.init 方法读取数据库 Access 表来替代手动输入配置)。
		// 但如果 Access 这张表的对外表名与数据库实际表名不一致，仍然需要这里注册。例如
		//		TABLE_KEY_MAP.put(Access.class.getSimpleName(), "access");

		// 表名映射，隐藏真实表名，对安全要求很高的表可以这么做
		//		TABLE_KEY_MAP.put(User.class.getSimpleName(), "apijson_user");
		//		TABLE_KEY_MAP.put(Privacy.class.getSimpleName(), "apijson_privacy");

		// 主键名映射
		SIMPLE_CALLBACK = new SimpleCallback<Long>() {

			@Override
			public AbstractSQLConfig<Long, JSONObject, JSONArray> getSQLConfig(
					RequestMethod method, String database, String datasource, String namespace
					, String catalog, String schema, String table) {
				return new DemoSQLConfig(method, table);
			}

			//取消注释来实现自定义各个表的主键名
			//			@Override
			//			public String getIdKey(String database, String datasource, String namespace, String catalog, String schema, String table) {
			//				//	return "_id"; // SurrealDB 强制用 id 作为主键名，surrealdb.java 查不到也改不了，所以需要另外加主键
			//				return StringUtil.firstCase(table + "Id");  // userId, comemntId ...
			//				//	return StringUtil.toLowerCase(t) + "_id";  // user_id, comemnt_id ...
			//				//	return StringUtil.toUpperCase(t) + "_ID";  // USER_ID, COMMENT_ID ...
			//			}

			@Override
			public String getUserIdKey(String database, String datasource, String namespace, String catalog, String schema, String table) {
				return USER_.equals(table) || PRIVACY_.equals(table) ? ID : USER_ID; // id / userId
			}

			// 取消注释来实现数据库自增 id
			//			@Override
			//			public Long newId(RequestMethod method, String database, String datasource, String namespace, String catalog, String schema, String table) {
			//				return null; // return null 则不生成 id，一般用于数据库自增 id
			//			}

			//			@Override
			//			public void onMissingKey4Combine(String name, JSONObject request, String combine, String item, String key) throws Exception {
			////				super.onMissingKey4Combine(name, request, combine, item, key);
			//			}
		};

	}


	// 如果 DemoSQLExecutor.getConnection 能拿到连接池的有效 Connection，则这里不需要配置 dbVersion, dbUri, dbAccount, dbPassword

	@Override
	public String gainDBVersion() {
		return "8.0.11"; //TODO 改成你自己的 MySQL 或 PostgreSQL 数据库版本号 //MYSQL 8 和 7 使用的 JDBC 配置不一样
	}

	private String dbUri;
	public DemoSQLConfig setDBUri(String dbUri) {
		this.dbUri = dbUri;
		return this;
	}
	@Override
	public String gainDBUri() {
		if (StringUtil.isNotEmpty(dbUri)) {
			return dbUri;
		}

		// 这个是 MySQL 8.0 及以上，要加 userSSL=false
		// return "jdbc:mysql://47.122.25.116:3306?userSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
		// 以下是 MySQL 5.7 及以下
		return "jdbc:mysql://localhost:3306?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8"; //TODO 改成你自己的，TiDB 可以当成 MySQL 使用，默认端口为 4000
		// return "jdbc:mysql://apijson.cn:3306?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8"; //TODO 改成你自己的，TiDB 可以当成 MySQL 使用，默认端口为 4000
	}

	private String dbAccount;
	public DemoSQLConfig setDBAccount(String dbAccount) {
		this.dbAccount = dbAccount;
		return this;
	}
	@Override
	public String gainDBAccount() {
		if (StringUtil.isNotEmpty(dbAccount)) {
			return dbAccount;
		}

		return "root"; //TODO 改成你自己的
		// return "apijson";  //TODO 改成你自己的
	}

	private String dbPassword;
	public DemoSQLConfig setDBPassword(String dbPassword) {
		this.dbPassword = dbPassword;
		return this;
	}
	@Override
	public String gainDBPassword() {
		if (StringUtil.isNotEmpty(dbPassword)) {
			return dbPassword;
		}

		return "apijson";  //TODO 改成你自己的，TiDB 可以当成 MySQL 使用， 默认密码为空字符串 ""
	}

	private String sql;
	public String gainSQL() throws Exception {
		return gainSQL(isPrepared());
	}
	@Override
	public String gainSQL(boolean prepared) throws Exception {
		if (StringUtil.isNotEmpty(sql)) {
			return sql;
		}
		return super.gainSQL(prepared);
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@Override
	protected int getMaxCombineCount() {
		return 10;
	}
}
