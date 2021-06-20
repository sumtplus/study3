package xyz.sumtplus.domain;

import lombok.Data;

/**
 * 첨부파일클래스
 *
 * @author 박재우
 * @date   2021. 5. 29.
 */
@Data
public class AttachFileDTO {
	private String uuid; //uuid
	private String fileName; //실제파일명
	private boolean image; //이미지여부
	private String uploadPath; //경로
	// 다운로드 경로를 반환하는 메서드
	public String getDownPath(){
		return uploadPath + "/" + uuid + "_" + fileName;
	}
	// 이미지 경로를 반환하는 메서드
	public String getThumbPath(){
		return uploadPath + "/s_" + uuid + "_" + fileName;
	}
}
