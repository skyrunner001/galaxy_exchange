package com.galaxy.exchange.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.SystemPropertyUtils;

import com.galaxy.exchange.common.exception.ExchangeRuntimeException;

public class ExchangeConfig {
	private static Properties props = null;

	/**
	 * 解析property的placeholder工具
	 */
	private static PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(
	        SystemPropertyUtils.PLACEHOLDER_PREFIX, SystemPropertyUtils.PLACEHOLDER_SUFFIX,
	        SystemPropertyUtils.VALUE_SEPARATOR, false);

	private static Locale defaultLocale;

	private static HashSet<String> logOperationPermits;

	private static Map<String, String> clientName2Password = null;

	private static Set<String> webServiceAllowedIps = null;

	private static Set<String> httpServiceAllowedIps = null;

	/**
	 * 根据配置的key
	 */
	public static String getConfigValueByKey(String configKey) {
		if (props == null) {

			return null;
		}
		return getProperty(configKey);
	}

	/**
	 * 根据给定的key返回对应的配置值
	 */
	public static String getConfigValueByKey(String key, String defaultValue) {
		String value = getConfigValueByKey(key);

		if (StringUtils.isEmpty(value)) {
			value = defaultValue;
		}

		return value;
	}

	public static String getConfigValueByKey(String key, Properties properties) {
		if (props == null) {
			return null;
		}
		return getProperty(key, properties);
	}

	/**
	 * 根据给定的值返回字符串数组 默认是分号(;)
	 * 
	 * @param key
	 *            关键字
	 */
	public static String[] getConfigValueArrayByKey(String key) {
		return getConfigValueArrayByKey(key, "\\;");
	}

	/**
	 * 根据给定的值返回字符串数组
	 * 
	 * @param key
	 *            关键字
	 * @param splitSymbol
	 *            拆分字符串的特殊符号
	 */
	public static String[] getConfigValueArrayByKey(String key, String splitSymbol) {
		String value = getConfigValueByKey(key);
		String[] valueArray = null;

		if (value != null) {
			valueArray = value.split(splitSymbol);
		}

		return valueArray;
	}

	/**
	 * 根据给定的值返回字符串数组,并由iso8859转换成utf-8
	 */
	public static String[] getConfigIsoValue2GbArrayByKey(String key, String splitSymbol) {
		String value = getConfigValueByKey(key);
		String[] valueArray = null;

		if (value != null) {
			valueArray = value.split(splitSymbol);
		}

		return valueArray;
	}

	/**
	 * 根据给定的值返回ArrayList,默认是分号(;)
	 * 
	 * @param key
	 *            关键字
	 */
	public static List<String> getConfigValueListByKey(String key) {
		return getConfigValueListByKey(key, "\\;");
	}

	/**
	 * 根据给定的值返回ArrayList
	 * 
	 * @param key
	 *            关键字
	 * @param splitSymbol
	 *            拆分字符串的特殊符号
	 */
	public static List<String> getConfigValueListByKey(String key, String splitSymbol) {
		List<String> valueList = new ArrayList<String>();
		String[] strings = getConfigValueArrayByKey(key, splitSymbol);
		if (strings != null) {
			Collections.addAll(valueList, strings);
		}
		return valueList;
	}

	/**
	 * 根据给定的值返回ArrayList,并将其中的iso转换成utf-8
	 */
	public static List<String> getConfigIsoValue2GbListByKey(String key, String splitSymbol) {
		ArrayList<String> valueList = new ArrayList<String>();
		Collections.addAll(valueList, getConfigIsoValue2GbArrayByKey(key, splitSymbol));

		return valueList;
	}

	private static String getProperty(String key, Properties properties) {
		if (StringUtils.isEmpty(key)) {
			throw new IllegalArgumentException("key is empty!");
		}
		if (!props.containsKey(key)) {
			throw new IllegalArgumentException("can't find this key:" + key);
		}
		return helper.replacePlaceholders(props.getProperty(key), properties);
	}

	private static String getProperty(String key) {
		return getProperty(key, props);
	}

	/**
	 * 是否需要验证
	 */
	public static boolean isNeedValidateAccessWs() {
		if (clientName2Password == null) {
			clientName2Password = new HashMap<String, String>();
			String configs = getConfigValueByKey("com.baidu.permit.clients");
			if (StringUtils.isBlank(configs)) {
				return false;
			}
			StringTokenizer st = new StringTokenizer(configs, ";");
			while (st.hasMoreElements()) {
				String config = st.nextToken();
				String[] strs = config.split(":");
				if (strs != null && strs.length >= 2) {
					clientName2Password.put(strs[0], strs[1]);
				}
			}
		}

		return !clientName2Password.isEmpty();

	}

	/**
	 * webService是否允许的IP
	 * 
	 * @param ip
	 *            String
	 * @return boolean
	 */
	public static boolean isPermitAccessHTTPByIp(String ip) {
		if (httpServiceAllowedIps == null) {
			httpServiceAllowedIps = new HashSet<String>();

			String ips = getConfigValueByKey(Constants.HTTP_ALLOWED_IP_KEY);

			if (StringUtils.isBlank(ips)) {
				return true;
			}
			if (ips != null) {
				ips = ips.replaceAll("\\t|\\n|\\s|\\r", ""); // 去除空格等

				String[] ipArray = ips.split(Constants.IP_SEPERATER);
				httpServiceAllowedIps.addAll(Arrays.asList(ipArray));
			}
		}
		return httpServiceAllowedIps.isEmpty() || httpServiceAllowedIps.contains(ip);
	}

	/**
	 * webService是否允许的IP
	 * 
	 * @param ip
	 *            String
	 * @return boolean
	 */
	public static boolean isPermitAccessWSByIp(String ip) {
		if (webServiceAllowedIps == null) {
			webServiceAllowedIps = new HashSet<String>();

			String ips = getConfigValueByKey(Constants.WS_ALLOWED_IP_KEY);

			if (StringUtils.isBlank(ips)) {
				return true;
			}
			if (ips != null) {
				ips = ips.replaceAll("\\t|\\n|\\s|\\r", ""); // 去除空格等

				String[] ipArray = ips.split(Constants.IP_SEPERATER);
				webServiceAllowedIps.addAll(Arrays.asList(ipArray));
			}
		}
		return webServiceAllowedIps.isEmpty() || webServiceAllowedIps.contains(ip);
	}

	/*---------------------------------------------------------------------------------------------------------*/
	public static Locale getDefaultLocale() {
		if (defaultLocale == null) {
			defaultLocale = Locale.SIMPLIFIED_CHINESE;
		}
		return defaultLocale;
	}

	/**
	 * 获取邮件发送人
	 * 
	 * @return DOCUMENT ME!
	 */
	public static String getMailFromUser() {
		return getConfigValueByKey("com.baidu.mailfrom");
	}

	/**
	 * webService是否允许的IP
	 * 
	 * @param methodName
	 *            String
	 * @return boolean
	 */
	public static boolean isPermitOutputLog(String methodName) {
		if (logOperationPermits == null) {
			logOperationPermits = new HashSet<String>();
			logOperationPermits.addAll(getNotOutputNames());
		}

		return logOperationPermits.contains(methodName);
	}

	/**
	 * 获取输出log的方法名称列表
	 */
	public static List<String> getNotOutputNames() {
		return getConfigValueListByKey("com.baidu.log.not.output", ",");
	}

	/**
	 * DOCUMENT ME!
	 */
	public static Set<String> getAdminMailList() {
		String adminEmail = getConfigValueByKey("tn_admin");
		return transferStringArrayToSet(adminEmail == null ? null : adminEmail.split(","));
	}

	public static Set<String> getUnionMailGroupList() {
		String unionMailGroup = getConfigValueByKey("union_mail_group");
		return transferStringArrayToSet(unionMailGroup == null ? null : unionMailGroup.split(","));
	}

	public static Set<String> getFeGroupEmails() {
		String feMailGroup = getConfigValueByKey("fe_mail_group");
		return transferStringArrayToSet(feMailGroup == null ? null : feMailGroup.split(","));
	}

	private static Set<String> transferStringArrayToSet(String... array) {
		if (array == null) {
			return new HashSet<String>();
		}
		return new HashSet<String>(Arrays.asList(array));
	}

	public static String getLocalTempDir() {
		String tempDir = getConfigValueByKey("file.temp.directory");
		if (!tempDir.endsWith("/")) {
			tempDir = tempDir + "/";
		}
		try {
			FileUtils.forceMkdir(new File(tempDir));
		} catch (IOException e) {
			throw new ExchangeRuntimeException(e);
		}
		return tempDir;
	}

	public static String getLocalBackupDir() {
		String backupDir = getConfigValueByKey("file.backup.directory");
		if (!backupDir.endsWith("/")) {
			backupDir = backupDir + "/";
		}
		return backupDir;
	}

	public static String getLocalInitialDir() {
		String initialDir = getConfigValueByKey("file.initial.directory");
		if (!initialDir.endsWith("/")) {
			initialDir = initialDir + "/";
		}
		return initialDir;
	}

	public static boolean isMockEnable() {
		return Boolean.valueOf(getConfigValueByKey("system.mock", "false"));
	}

	public void setProps(List<Properties> lists) {
		Properties prop = new Properties();
		for (Properties properties : lists) {
			prop.putAll(properties);
		}
		ExchangeConfig.props = prop;
	}
}
