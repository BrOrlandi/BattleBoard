package Item;

import java.util.ArrayList;
import java.util.Iterator;


public class ItemStore {
	private String mName;
	private String mDescription;
	private ArrayList<Item> mItems;
	
	public ItemStore(String name, String desc){
		mName = name;
		mDescription = desc;
		mItems = new ArrayList<Item>();
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String name) {
		mName = name;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String desc) {
		mDescription = desc;
	}
	
	public boolean addItem(Item item){
		return mItems.add(item);
	}
	
	public Item removeItem(int pos){
		return mItems.remove(pos);
	}
	
	public boolean removeItem(Item i){
		return mItems.remove(i);
	}
	
	public String[] getItemsString(){
		String[] array = new String[mItems.size()];
		Iterator it = mItems.iterator();
		int i=0;
		while(it.hasNext()){
			array[i] = it.next().toString();
			i++;
		}
		return array;
	}
}
