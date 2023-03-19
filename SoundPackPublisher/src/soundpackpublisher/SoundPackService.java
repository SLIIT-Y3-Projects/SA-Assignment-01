package soundpackpublisher;

public interface SoundPackService {
	
	void addPackage();
	void getAllPackages();
	void getPackageByID();
	void deletePackage();
	float getPrice(int packId);
	void calTotalSoundsFee();

}
