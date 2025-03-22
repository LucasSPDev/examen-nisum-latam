package com.nisum.examen.base;

public class Constants {

	public static String CODE_OK = "00";
	public static String CODE_OK_DESCRIPTION = "OK";

	public static String CODE_GENERAL_ERROR = "01";
	public static String CODE_GENERAL_ERROR_DESC = "General Error";

	public static String CODE_NAME_USER = "02";
	public static String CODE_NAME_USER_DESC = "Error en el campo 'name', no es valido";

	public static String CODE_EMAIL_FORMAT = "03";
	public static String CODE_EMAIL_FORMAT_DESC = "Error en el campo 'email', no es valido (Ej: examen@nisum.com)";

	public static String CODE_PASSWORD_FORMAT = "04";
	public static String CODE_PASSWORD_FORMAT_DESC = "Error en el campo 'paswword', no es valido (Ej: Ab123456789)";

	public static String CODE_PHONE_USER = "05";
	public static String CODE_PHONE_USER_DESC = "Error en el campo 'phone', no es valido";

	public static String CODE_ERR_CREATE_USER = "06";
	public static String CODE_ERR_CREATE_USER_DESC = "Error en la creacion del usuario";

	public static String CODE_ERR_QUERY_USER = "07";
	public static String CODE_ERR_QUERY_USER_DESC = "Usuario no existe";
	
	public static String CODE_ERR_CREATE_USER_BY_EMAIL = "08";
	public static String CODE_ERR_CREATE_USER_BY_EMAIL_DESC = "El email ya se encuentra registrado";
	
	public static String CODE_ERR_MODIFY_USER_BY_EMAIL = "09";
	public static String CODE_ERR_MODIFY_USER_BY_EMAIL_DESC = "El email NO se encuentra registrado";
	
	public static String CODE_ERR_MODIFY_USER = "10";
	public static String CODE_ERR_MODIFY_USER_DESC = "Error en la actualización del usuario";
	
}
