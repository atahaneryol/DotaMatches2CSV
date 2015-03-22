package trial;

public class Hero
{
	private String name;
	private Long id;
	private String localized_name; // Would rather have localizedName, but the api forces me to name this way
	
	public Hero() {
	}
	
	public Hero(String name, Long id) {
		this.name = name;
		this.id = id;
	}
	
	public String getLocalized_name() {
		return localized_name;
	}

	public void setLocalized_name(String localized_name) {
		this.localized_name = localized_name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
