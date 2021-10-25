package assesment2;
//乘客类
public class Passengers {
	private String name;
	private int age;
	private String diet;//定义饮食习惯
	public Passengers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Passengers(String name, int age, String diet) {
		super();
		this.name = name;
		this.age = age;
		this.diet = diet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	@Override
	public String toString() {
		return "Passengers [name=" + name + ", age=" + age + ", diet=" + diet + "]";
	}
	
	
}
