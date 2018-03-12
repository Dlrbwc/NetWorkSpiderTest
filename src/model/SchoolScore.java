package model;

public class SchoolScore {
	
	private String name;
	
	private String province;
	
	private String classical;
	
	private String year;
	
	private String batch;
	
	private String lowScore;
	
	private String higtScore;
	
	private String averageScore;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getLowScore() {
		return lowScore;
	}

	public void setLowScore(String lowScore) {
		this.lowScore = lowScore;
	}

	public String getHigtScore() {
		return higtScore;
	}

	public void setHigtScore(String higtScore) {
		this.higtScore = higtScore;
	}

	public String getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getClassical() {
		return classical;
	}

	public void setClassical(String classical) {
		this.classical = classical;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "学校: " + name + ", 省市: " + province + ", 类别: " + classical +
				", 年份: " + year + ", 最低分: " + lowScore +
				", 最高分: " + higtScore + ", 平均分: " + averageScore +
				", 批次: " + batch;
	}
	
	
}
