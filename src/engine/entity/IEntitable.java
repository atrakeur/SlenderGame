package engine.entity;

public interface IEntitable {

	public abstract void onCreate();

	public abstract void onDestroy();

	public abstract String getName();

	public abstract void setName(String name);

}