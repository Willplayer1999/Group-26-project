public interface Collidable {
		
		public int getWidth();
		
		public int getHeight();
		
		public int getRadius();
		
		public int getX();
		
		public int getY();
		
		public boolean collidedWith(Collidable c);

}