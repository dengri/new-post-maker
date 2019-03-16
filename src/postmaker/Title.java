package postmaker;

public class Title extends PostItem {
	
	public Title(String value) {
		super(value);
	}

	@Override
	public void setValue(String value) {
		this.value = value.replaceAll("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}-", "");
		this.value = this.value.replaceAll("SiteRip|siterip|\\[Asian Vault\\]|WEB|DL|AAC2|H.264", "");
	}
	
	@Override
	public int setNumber(String value) {
		return Integer.parseInt(value.split("-")[0]
				.split("\\.")[2]);
	}

}
