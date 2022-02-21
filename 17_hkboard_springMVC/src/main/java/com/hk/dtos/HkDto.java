package com.hk.dtos;
//data를 저장하고 관리하는 객체

import java.util.Date;



import lombok.Data;
@Data
public class HkDto {

		private int seq;
		private String id;
		private String title;
		private String content;
		private Date regdate;
		
		public HkDto(int seq, String title, String content) {
			super();
			this.seq = seq;
			this.title = title;
			this.content = content;
		}
		public HkDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "HKdto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", regdate="
					+ regdate + "]";
		}
		public int getSeq() {
			return seq;
		}
		public HkDto(String id, String title, String content) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Date getRegdate() {
			return regdate;
		}
		public void setRegdate(Date regdate) {
			this.regdate = regdate;
		}
		public HkDto(int seq, String id, String title, String content, Date regdate) {
			super();
			this.seq = seq;
			this.id = id;
			this.title = title;
			this.content = content;
			this.regdate = regdate;
		}
}
