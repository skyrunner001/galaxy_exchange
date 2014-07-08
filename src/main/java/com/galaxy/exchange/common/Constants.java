package com.galaxy.exchange.common;

/**
 * 通用常量定义类，项目中的所有常量定义在此，避免不同模块开发人员的重复定义
 */
public class Constants {

    /** 邮件抄送时分隔符 */
    public static final String BPM_ACT_COMPLETE_MAIL_SEPERATER = ";";

    /** 配置的默认分隔符 */
    public static final String CONFIG_LOCATION_SEP = ",";

    /** 页面以及分页时最大显示的默认记录数， */
    public static final int PAGE_MAX_SHOW_RECORDS = 10;
    
    /** 页面以及分页时最大显示的默认记录数(为了方便上面显示列表，下面显示详情，专门定义的) */
    public static final int PAGE_MAX_SHOW_RECORDS_SMALL = 5;
    
    /** 页面以及分页时最大显示的默认记录数*/
    public static final int PAGE_MAX_SHOW_RECORDS_LARGE = 15;
    /** 初始化时最大选取数 */
    public static final int INIT_MAX_RECORDS = 5000;

    /** 页面显示的的可直接看到的页数 */
    public static final int MAX_SHOW_PAGE = 5;

    /** ip白名单的标识 */
    public static final String WS_ALLOWED_IP_KEY = "com.galaxy.permit.ips";
    
    /** ip白名单的标识 */
    public static final String HTTP_ALLOWED_IP_KEY = "com.galaxy.permit.http.ips";
    
    /** ip白名单中ip的分隔符 */
    public static final String IP_SEPERATER = ",";
}
