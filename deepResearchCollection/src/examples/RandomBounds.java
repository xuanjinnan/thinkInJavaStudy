package examples;

public class RandomBounds {
	static void usage() {
		System.out.println("usage");
		System.out.println("\tRandomBounds lower");
		System.out.println("\tRandomBounds upper");
		System.exit(1);
	}
	public static void main(String[] args) {
		args = new String[]{"lower"};
		if(args.length != 1)
			usage();
		if(args[0].equals("lower")) {
			double random = Math.random();
			while(random != 0.0) {
				System.out.println(random);
				random = Math.random();
			}
			System.out.println("Produced 0.0!");
		}else if(args[0].equals("upper")) {
			while(Math.random() != 1.0)
				;
			System.out.println("Produced 1.0!");
		}else
			usage();
	}
}
