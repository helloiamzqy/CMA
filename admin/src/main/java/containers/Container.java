package containers;

import java.io.Serializable;
import java.util.Collection;

public interface Container<T,Id extends Serializable> {

	/**
	 * 添加新的元素
	 * @param
	 */
	public void add(T bean, Id id) ;
	
	/**
	 * 获取指定id 为pk的元素
	 * @param
	 * @return
	 */
	public T get(Id id);
	
	/**
	 * 删除id为pk的
	 * @param
	 */
	public void remove(Id id);
	

	/**
	 * 获取集合中id列表
	 * @return
	 */
	public Collection<Id> getKeys();

	

}
