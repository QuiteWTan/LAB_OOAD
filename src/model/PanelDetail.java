package model;

public class PanelDetail {
	private Integer panelId;
	private Integer userId;
	
	public PanelDetail(Integer panelId, Integer userId) {
		super();
		this.panelId = panelId;
		this.userId = userId;
	}

	public Integer getPanelId() {
		return panelId;
	}

	public void setPanelId(Integer panelId) {
		this.panelId = panelId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
