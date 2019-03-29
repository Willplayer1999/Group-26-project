public class AvatarBullet extends Bullet{
	//Constructor for text Based
	public AvatarBullet(int x, int y) {
		super(x,y);
	}
	//Constructor for GUI
	public AvatarBullet(int x, int y, int momentum) {
        super(x, y, momentum);
    }
	//Copy Constructor
	public AvatarBullet(AvatarBullet a) {
		super(a);
	}
	@Override
    public boolean move() {
    	boolean edgy;
        if (getYCord() == 0) {
            edgy = false;
        } else {
            edgy = true;
            setYCord(getYCord() - getMomentum());
        }
        return edgy;
    }
}