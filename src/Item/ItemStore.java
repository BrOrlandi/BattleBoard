package Item;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * Loja de Items contém uma agregação de itens que podem ser comprados pelos jogadores para aplicar aos seus personagens.
 *
 */
public class ItemStore {
	private String mName; ///< Nome da Loja
	private String mDescription; ///< Descrição da Loja
	private ArrayList<Item> mItems; ///< Lista de Itens na loja.
	
	/**
	 * Construtor de ItemStore
	 * @param name Nome da loja
	 * @param desc Descrição da loja
	 */
	public ItemStore(String name, String desc){
		mName = name;
		mDescription = desc;
		mItems = new ArrayList<Item>();
	}

	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		mName = name;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String desc) {
		mDescription = desc;
	}
	
	/**
	 * Adiciona um item à loja.
	 * @param item item a ser adicionado.
	 * @return verdadeiro se o item foi adicionado.
	 */
	public boolean addItem(Item item){
		return mItems.add(item);
	}
	
	/**
	 * Remove um item da loja, deve ser usado quando um jogador comprar o item.
	 * @param pos posição do item na lista.
	 * @return o item que foi removido.
	 */
	public Item removeItem(int pos){
		return mItems.remove(pos);
	}
	
	/**
	 * Pega uma lista de strings que são a representação de cada item em string.
	 * Deve ser usado para imprimir a lista de items da loja.
	 * @return uma lista de Strings representando os items.
	 */
	public String[] getItemsString(){
		String[] array = new String[mItems.size()];
		Iterator<Item> it = mItems.iterator();
		int i=0;
		while(it.hasNext()){
			array[i] = it.next().toString();
			i++;
		}
		return array;
	}
}
