package SS18.PROG2;

public class snippets {

	String.format("%8.2f %02d %-8d", varA, varB, varC) // 8digits+2 after dec.point, with leading zeros, left aligned
	text.substring(0,10) //starting from index 0 and ending at 10
	.trim //It returns a copy of this string with leading and trailing white space removed
	int numb = Integer.parseInt("40"); //string auf int parsen
	.replaceAll(" +", " "); //replaces all whitespaces to one whitespace
	if (text.matches("\\d* +\\d*") == truöe) { ... // checkt ob text dem regex ausdruck entspricht
	t.getTitle().indexOf(string-var) == 0 // sucht im Title nach einem string - definiert in string-var

	String text = "004-034556";
	String[] parts = text.split("-");
	String part1 = parts[0]; // 004
	String part2 = parts[1]; // 034556

	public class Album extends Release { ...
		public Album() {
				super(); ... //calls constructor of superclass - Release

	max = (a>b) ? a : b --> if condition true than a else b
	String strDate = this.date != null ? this.date.toString() : "null";

	
//LISTS SETS COLLECTIONS
	List<String> li = new LinkedList<String>();
	li.get(0);
	li.set(0, "test");
	Object li2 = li.clone();

	List<Track> li = new ArrayList<Track>();
	ArrayList<String> obj = new ArrayList<String>();
	obj.add("Ajeet") bzw. obj.add(0, "Rahul")
	obj.remove("Harry") bzw. obj.remove(1)
	obj.contains("Henri") //check ob Henri in Liste enthalten ist
	obj.clear() //removing all the elements
	obj.toArray() //returns array containing all elements in this list
	for(String ausgabe : obj) { System.out.println(ausgabe); } // liste durchiterieren, jedes element printen
	System.out.println("Currently the array list has following elements:"+obj); //output: Currently the array list has following elements:[Ajeet, Harry, Chaitanya, Steve, Anuj]

	ArrayList<Integer> liste = new ArrayList<Integer>(); 
	Iterator<Integer> li = liste.Iterator(); 
	while(li.hasNext()) { ... li.next() ...
	li.remove() //Removes the last element that was returned by next(), nur bei iterator nicht bei for each möglich

	Collections.sort(obj); //sorted alphabetically, for list of custom type objects implement Comparable first
	public class Student implements Comparable { ...
	@Override
	    public int compareTo(Student comparestu) {
		int compareage=((Student)comparestu).getStudentage();
	        return this.studentage-compareage; //ascending
			
	Private person1 = new Private ("Max", "Mustermann", 10, null);
	List<Kunde> KundenListe = new LinkedList<Kunde>();
	KundenListe.add(person1) bzw. KundenListe.add(new Private("Max", "Mustermann", 10, null));


	HashSet<String> hset = new HashSet<String>(); //no duplicates, add duplicate will overwrite old one
	TreeSet<Integer> tset2 = new TreeSet<Integer>(); //as HashSet but sorted ascending
	Set<String> tset = new TreeSet<String>(hset); // HashSet in TreeSet umwandeln
	List<String> list = new ArrayList<String>(hset); creating a List of HashSet elements
	

//COMPARE, COMPARETO
	int compareTo(T o) in Comparable: returns 0 if equal, -1 if variable in () - 'o' is greater/ 1 if less
	int compare(T o1, T o2) in Comparator // if we want to have multiple sort choices: Returns - int o1<o2 , 0 o1=o2 ->, + int o1>o2

	public class BookNameComparator implements Comparator<Author>{ 
	  public int compare(Author a1,Author a2){   
	     return a1.bookName.compareTo(a2.bookName);  ...
	Collections.sort(li, new BookNameComparator());

	int[] arr = {11,55,22,0,89};
	    Arrays.sort(arr);
	    System.out.println(Arrays.toString(arr));

//EXCEPTIONS
	try {...}; 
	catch (FileNotFoundException e) { e.printStackTrace(); ...} //use with reader
	catch (IOException e) {...] // use with reader
	catch (Exception e) {...} //generic Exception handler
	finally {...} //will be executed whether exception occurs or not
	 ...throw new IOException("line == null");

//READER	 
	Reader in = new BufferedReader(new FileReader("C:\\myfile.txt"));
	int c;
	while ((c = in.read()) != -1) { ... //reads a single char vs. readLine reads line of text
	String contentLine = in.readLine();
	while (contentLine != null) {
		System.out.println(contentLine);

	Scanner sc = new Scanner(System.in);
	switch (sc.next()) { case "1": ...}	
	

//DEEPCOPY
	this.artist = new Artist(e.artist); 
	this.description = e.description;

	//creates a deep copy of an event
	public Event (Event e) {
		this.artist = new Artist(e.artist);
		this.venue = new Venue(e.venue);
		this.date = new Date(e.date);
		this.description = e.description;
		this.attendees = e.attendees;

	//deep copy when array is input
	private Track[] deepCopy(Track[] inputTracks) {
		if(inputTracks == null) {
			return new Track[0];
		}
		
		int newLength = inputTracks.length;
		for (int i=0; i < inputTracks.length; i++) {
			if(inputTracks[i] == null) {
				newLength--;
			}
		}
		
		Track[] newSetList = new Track[newLength];
		int count = 0;
		for(int i=0; i<inputTracks.length; i++) {
			if(inputTracks[i] != null) {
				newSetList[count]= new Track(inputTracks[i]);
				count++;
			}
		}
		return newSetList;
	}
		
}
