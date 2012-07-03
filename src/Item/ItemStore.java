package Item;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 
 * Loja de Items contém uma agregação de itens que podem ser comprados pelos jogadores para aplicar aos seus personagens.
 *
 */
public class ItemStore {
	private String mName; ///< Nome da Loja
	private SortedSet<Item> mItems; ///< Lista de Itens na loja.
	
	/**
	 * Construtor de ItemStore
	 * @param name Nome da loja
	 */
	public ItemStore(String name){
		mName = name;
		mItems = new TreeSet<Item>();
	}

	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		mName = name;
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
	 * @param item referencia do item a ser removido.
	 * @return true se foi removido com sucesso.
	 */
	public boolean removeItem(Item item){
		return mItems.remove(item);
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
	
	/**
	 * Permite acessar os items da loja em um vetor.
	 * @return Array com os Items da loja.
	 */
	public Item[] getItems(){
		return (Item[])mItems.toArray();
	}
}
