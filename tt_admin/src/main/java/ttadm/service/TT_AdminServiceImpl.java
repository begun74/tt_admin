package ttadm.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.modelattribute.MA_search;
import ttadm.dao.Dao;
import ttadm.dao.DaoImpl;
import ttadm.model.ContactUsMessages;
import ttadm.model.DirGender;
import ttadm.model.DirNomenclGroup;
import ttadm.model.DirNomenclGroupRoot;
import ttadm.model.DirNomenclature;
import ttadm.model.DirProvider;
import ttadm.model.Order;
import ttadm.model.OrderItems;
import ttadm.model.Store;
import ttadm.model.Tail;
import ttadm.model.User;

@Service("ttadmService")
@Transactional()
public class TT_AdminServiceImpl implements Dao {
	
	@Autowired
	private DaoImpl dao;
	
	private  static  final  Log  LOG  =  LogFactory.getLog(TT_AdminServiceImpl.class);
	

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DirProvider> getProviderList() {
		// TODO Auto-generated method stub
		return dao.getProviderList();
	}

	@Override
	public List<DirProvider> getProviderListInTails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProvider(DirProvider dirProvider) {
		// TODO Auto-generated method stub

	}

	@Override
	public DirProvider getProviderByCode(Integer code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirNomenclature> getNomenclatureList() {
		// TODO Auto-generated method stub
		return dao.getNomenclatureList();
	}

	@Override
	public List<DirNomenclature> getNomenclatureList(Collection<Criterion> criterions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNomenclature(DirNomenclature dirNomenclature) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<DirNomenclature> getTailsNomenclature(Tail tail_example, Collection<Criterion> criterions, int p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<DirNomenclature> getNomenclInTails(MA_search mA_search, int p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<DirNomenclature> getNomenclInTails_(List<Long> types, List<Long> providers, List<Long> genders,
			List<Long> categories, int p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirNomenclature> getPopularDirNomenclature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirNomenclature> getNomenclOfProvider(Long id_dir_provider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirNomenclGroup> getNomenclGroupList() {
		// TODO Auto-generated method stub
		return dao.getNomenclGroupList();
	}

	@Override
	public List<DirNomenclGroup> getNomenclGroupListInTails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNomenclGroup(DirNomenclGroup dirNomenclGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DirNomenclGroupRoot> getNomenclGroupRootList() {
		// TODO Auto-generated method stub
		return dao.getNomenclGroupRootList();
	}

	@Override
	public List<DirNomenclGroupRoot> getNomenclGroupRootListInTails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNomenclGroupRoot(DirNomenclGroupRoot dirNomenclGroupRoot) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DirGender> getGenderList() {
		// TODO Auto-generated method stub
		return dao.getGenderList();
	}

	@Override
	public DirGender getGenderByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTail(Tail tail) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tail> getTailsList() {
		// TODO Auto-generated method stub
		return dao.getTailsList();
	}

	@Override
	public List<Tail> getTailsList(Tail tail_example, Collection<Criterion> criterions, int p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tail> getTailsList(long id_dirNomenclature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DirNomenclature> findByText(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> getStoreList() {
		// TODO Auto-generated method stub
		return dao.getStoreList();
	}

	@Override
	public void addStore(Store store) {
		// TODO Auto-generated method stub

	}

	@Override
	public Store getStoreBySerVerUID(Long serialVersionUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItems> getOrderItems(Long order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrderItems(List<OrderItems> listOI) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public Order getOrder(Long order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrdersList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addContactUsMessages(ContactUsMessages contactUsMessages) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ContactUsMessages> getContactUsMessagesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(Class clazz, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delObject(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTails() {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger countGender(Long id_gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger countCategory(Long id_category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger countProvider(Long id_provider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger countType(Long id_type) {
		// TODO Auto-generated method stub
		return null;
	}

}
