
/**
* Group 26 CPSC 219 Project TUT 06
* Interface class for checking whether two objects have collided and depending which objects collide procedures may or may not be executed to handle the collision, which are specified on the classes that implement this interface.
*/
public interface Collidable
{
	/**
	* Method to be overriden by any class that implements this interface. It will get the width of any object it is specified to.
	* @return An integer that represents the width gotten.
	*/
	public int getWidth();

	/**
	* Method to be overriden by any class that implements this interface. It will get the height of any object it is specified to.
	* @return An integer that represents the height gotten.
	*/
	public int getHeight();

	/**
	* Method to be overriden by any class that implements this interface. It will get the radius of any object it is specified to.
	* @return An integer that represents the radius gotten.
	*/
	public int getRadius();

	/**
	* Method to be overriden by any class that implements this interface. It will get the x coordinate of any object it is specified to.
	* @return An integer that represents the x coordinate gotten.
	*/
	public int getX();

	/**
	* Method to be overriden by any class that implements this interface. It will get the y coordinate of any object it is specified to.
	* @return An integer that represents the y coordinate gotten.
	*/
	public int getY();

	/**
	* Method to be overriden by any class that implements this interface. It will check if any object specified has collided with the object given and handle the answer aproprietly.
	* @param c The object given that will be checked if it collided with an object specified.
	* @return A boolean that represents whether or not the two objects have collided.
	*/
	public boolean collidedWith(Collidable c);
}