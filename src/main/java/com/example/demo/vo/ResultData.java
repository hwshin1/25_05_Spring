package com.example.demo.vo;

import lombok.Getter;

public class ResultData<DT> {
	@Getter
	private String ResultCode;
	@Getter
	private String msg;
	@Getter
	private DT data1;
	@Getter
	private String data1Name;
	
	public static <DT>ResultData<DT> from(String ResultCode, String msg, String data1Name, DT data1) {
		ResultData rd = new ResultData();
		
		rd.ResultCode = ResultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		
		return rd;
	}
	
	public static <DT>ResultData<DT> from(String ResultCode, String msg) {
		return from(ResultCode, msg, null, null);
	}
	
	public boolean isSuccess() {
		return ResultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}

	public static <DT>ResultData<DT> newData(ResultData rd, String data1Name, DT newData) {
		return from(rd.getResultCode(), rd.getMsg(), data1Name, newData);
	}
}
