package savePhoto.model;

public class SavePhotoMgr {
	private static final SavePhotoMgr instance = new SavePhotoMgr();
	
	private SavePhotoDAO dao;
	
	private SavePhotoMgr() {
		dao = new SavePhotoDAO();
	}
	
	public static SavePhotoMgr getInstance() {
		return instance;
	}
	
	public void insertM(SavePhotoDTO dto) {
		dao.insert(dto);
	}
}
