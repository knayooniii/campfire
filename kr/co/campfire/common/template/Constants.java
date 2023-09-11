package kr.co.campfire.common.template;

public class Constants {
	// ◼◼◼◼◼◼◼◼◼◼ Member Register
	/*
	 * REGEX_EMAIL					/^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/
	 * (String) REGEX_EMAIL			"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
	 * REGEX_PASSWORD				/^(?=.*[a-zA-Z])(?=.*[@$!%*?&\#])[A-Za-z\d@$!%*?&\#]{6,20}$/
	 * (String) REGEX_PASSWORD		"^(?=.*[a-zA-Z])(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{6,20}$"
	 * */
	public final static String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
	public final static String REGEX_PASSWORD = "^(?=.*[a-zA-Z])(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{6,20}$";
	
	// ◼◼◼◼◼◼◼◼◼◼ Board List
	public final static int PAGE_RANGE_LIMIT_PER_PAGE_BLOCK = 15;
	public final static int LIST_RANGE_LIMIT_PER_PAGE = 10;
	
	// ◼◼◼◼◼◼◼◼◼◼ Board
	public final static int TITLE_SIZE_LIMIT_BYTES = 50;
	public final static int CONTENT_SIZE_LIMIT_BYTES = 50;
	public final static int FILE_SIZE_LIMIT_BYTES = 524288000; // 500MB
	
	// File
	public final static String FILE_UPLOAD_PATH = "C:\\Users\\Administrator\\git\\campfire\\Campfire\\src\\main\\webapp\\resources\\upload\\";

}
