package assesment2;

public class Test {
	public static void main(String[] args) {
		Journey[] name = new Journey[2];
		name[0] = new Journey("����-�ɶ�",19392,3399,10);
		name[1] = new Journey("����-տ��",32832,3399,10);
		Menu menu= new Menu(name);
		menu.menuProgram();
		
	}
}
