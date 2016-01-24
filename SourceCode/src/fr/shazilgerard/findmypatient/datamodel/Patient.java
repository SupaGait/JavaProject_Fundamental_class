/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel;

/**
 * @author Gerard
 *
 */
public class Patient {
	private String name;
	private String id;
	private String room;
	
	public Patient(String name, String id, String room)
	{
		this.name = name;
		this.id = id;
		this.room = room;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}

}
