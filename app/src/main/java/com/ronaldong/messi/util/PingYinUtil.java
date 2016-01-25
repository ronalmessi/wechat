package com.ronaldong.messi.util;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PingYinUtil {

	/**
	 * 将字符串中的中文转化为拼音,英文字符变小写
	 * 
	 * @param inputString
	 * @return
	 */
	public static String getPingYin(String inputString) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		StringBuffer output = new StringBuffer();
		try {
			for (char element : inputString.trim().toCharArray()) {
				String ele = Character.valueOf(element).toString();
				if (ele.matches("[\\u4E00-\\u9FA5]+")) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							element, format);
					output.append(temp[0]);
				} else {
					output.append(ele.toLowerCase());
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	/**
	 * 将字符串中的中文转化为{首字母 全拼}
	 * 
	 * @param inputString
	 * @return
	 */
	public static String getPingYin4Search(String inputString) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		StringBuffer all = new StringBuffer();
		StringBuffer first = new StringBuffer();
		if (TextUtils.isEmpty(inputString)) {
			return "";
		} else {
			try {
				for (char element : inputString.trim().toCharArray()) {
					String ele = Character.valueOf(element).toString();
					if (ele.matches("[\\u4E00-\\u9FA5]+")) {
						String[] temp = PinyinHelper.toHanyuPinyinStringArray(
								element, format);
						all.append(temp[0]);
						if (temp[0] != null) {
							first.append(temp[0].charAt(0));
						}
					} else {
						all.append(ele.toLowerCase());
					}
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
			return first.toString() + " " + all.toString() + " " + inputString;
		}
	}
}