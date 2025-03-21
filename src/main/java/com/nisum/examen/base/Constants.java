package com.nisum.examen.base;

public class Constants {
	
	public static String CODE_OK = "00";
	public static String CODE_OK_DESCRIPTION = "OK";
	
	public static String CODE_GENERAL_ERROR = "01";
	public static String CODE_GENERAL_ERROR_DESC = "General Error";
	
	public static String CODE_NAME_USER = "02";
	public static String CODE_NAME_USER_DESC = "Falta campo 'name' en la peticion";
	
	public static String CODE_EMAIL_FORMAT = "03";
	public static String CODE_EMAIL_FORMAT_DESC = "Formato del campo 'email', no es valido (Ej: examen@nisum.com)";
	
	public static String CODE_PASSWORD_FORMAT = "04";
	public static String CODE_PASSWORD_FORMAT_DESC = "Formato del campo 'paswword', no es valido (Ej: Ab123456789)";
	
	public static String CODE_PHONE_USER = "05";
	public static String CODE_PHONE_USER_DESC = "Falta campo phone en la peticion";
	
	public static String CODE_ERR_CREATE_USER = "06";
	public static String CODE_ERR_CREATE_USER_DESC = "El usuario no ha podido ser creado";
	
	public static String CODE_ERR_QUERY_USER = "07";
	public static String CODE_ERR_QUERY_USER_DESC = "Usuario no existe";
}
