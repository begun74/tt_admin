package ttadm.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import ttadm.model.Tail;
import ttadm.modelattribute.*;
import ttadm.model.*;



public interface Dao {
	
	
	public List<AdvertisingCampaign> getAdvCampList();
	public List<AdvertisingCampaign> getAdvCampList(boolean active);
	
	public List<User> getUserList();
	public void addUser(User user);


	public List<DirProvider> getProviderList();
	public List<DirProvider> getProviderListInTails();
	public void addProvider(DirProvider dirProvider);
	public DirProvider getProviderByCode(Integer code);
	

	public List<DirNomenclature> getNomenclatureList();
	public Object [] getNomenclatureList(int p, long itemOnPage, String sortby);
	public List<DirNomenclature> getNomenclatureList(Collection<Criterion> criterions);
	public void addNomenclature(DirNomenclature dirNomenclature);
	public Set<DirNomenclature> getTailsNomenclature(Tail tail_example, Collection<Criterion> criterions ,int p);
	public Set<DirNomenclature> getNomenclInTails(MA_search mA_search , int p);
	public Set<DirNomenclature> getNomenclInTails_(List<Long> types, List<Long> providers, List<Long> genders, List<Long> categories , int p);
	public List<DirNomenclature> getPopularDirNomenclature();
	public List<DirNomenclature> getNomenclOfProvider(Long id_dir_provider);


	public List<DirNomenclGroup> getNomenclGroupList();
	public List<DirNomenclGroup> getNomenclGroupListInTails();
	public void addNomenclGroup(DirNomenclGroup dirNomenclGroup);

	public List<DirNomenclGroupRoot> getNomenclGroupRootList();
	public List<DirNomenclGroupRoot> getNomenclGroupRootListInTails();
	public void addNomenclGroupRoot(DirNomenclGroupRoot dirNomenclGroupRoot);

	public List<DirGender> getGenderList();
	public DirGender getGenderByName(String name);

	
	public void addTail(Tail tail);
	public void addTails(Collection<Tail> tails);
	public List<Tail> getTailsList();
	public Object [] getTailsList(int p, long itemOnPage, String sortby);
	public List<Tail> getTailsList(Tail tail_example, Collection<Criterion> criterions, int p );
	public List<Tail> getTailsList(long id_dirNomenclature);
	public void callProc_DiffOfTails();
	
	public List<DirNomenclature> findByText(String text);

	public List<Store> getStoreList();
	public void addStore(Store store);
	public Store getStoreBySerVerUID(Long serialVersionUID);
	
	
	public List<OrderItems> getOrderItems(Long order);
	public void saveOrderItems(List<OrderItems> listOI);
	
	public void addOrder(Order order);
	public Order getOrder(Long order);
	public List<Order> getOrdersList();
	
	public void addContactUsMessages(ContactUsMessages contactUsMessages);
	public List<ContactUsMessages> getContactUsMessagesList();
	
	public Object getObject(Class clazz,Long id);
	public void delObject(Object obj);
	public void saveIModel(IModel imodel);
	
	public void updateTails();
	
	User findByUserName(String username);
	

}
