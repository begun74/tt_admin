package ttadm.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import ttadm.annotation.ProcessTail;
import ttadm.model.AdvertisingCampaign;
import ttadm.model.ContactUsMessages;
import ttadm.model.DirGender;
import ttadm.model.DirNomenclGroup;
import ttadm.model.DirNomenclGroupRoot;
import ttadm.model.DirNomenclature;
import ttadm.model.DirProvider;
import ttadm.model.IModel;
import ttadm.model.NewTails;
import ttadm.model.OrderItems;
import ttadm.model.Store;
import ttadm.model.Tail;
import ttadm.model.User;
import ttadm.modelattribute.MA_search;
import ttadm.util.Sortby;


@PropertySource("classpath:sql.properties")
@Repository("dao")
public class DaoImpl implements Dao {
	
    @Resource
    private Environment env;

	
	@Autowired
    private SessionFactory sessionFactory;
	
	
	protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }


	@Override
	public List<AdvertisingCampaign> getAdvCampList() {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("select * from advert_campaign order by active desc, fromdate, todate").addEntity(AdvertisingCampaign.class).list();
	}

	@Override
	public List<AdvertisingCampaign> getAdvCampList(boolean active) {
		// TODO Auto-generated method stub
		
		//List<AdvertisingCampaign> advCampList = getSession().createSQLQuery("select * from advert_campaign where active = :active").addEntity(AdvertisingCampaign.class).setParameter("active", active).list(); 
		
		List<AdvertisingCampaign> advCampList = getSession().createCriteria(AdvertisingCampaign.class).add(Restrictions.eq("active", active)).addOrder(Order.asc("index")).list();
		return advCampList;
	}
	
	
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
		return getSession().createSQLQuery("select * from dir_provider order by name").addEntity(DirProvider.class).list();
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
	public Object[] getNomenclatureList(int p, long itemOnPage,String sortby) {
		// TODO Auto-generated method stub
		
		Sortby sortby_enum = Sortby.name;
		try {
			sortby_enum = Sortby.valueOf(sortby);
		}
		catch(Exception exc) {
			sortby_enum = Sortby.name;
		}
		
		String order_ = sortby_enum.name(); 
		switch(sortby_enum) {
			case name:
				order_ = sortby_enum.name();
			break;
			case code:
				order_ = sortby_enum.name();
			break;
				
		}
		
		
		Object[] result = {null,null};
		result[0] = ((BigInteger )getSession().createSQLQuery("select count(dn.*) from dir_nomenclature dn").uniqueResult()).longValue();


		if(itemOnPage < 0)
		{
			p=1;
			itemOnPage = (long) result[0];
		}
		
		result[1] = (List<DirNomenclature>)getSession().createSQLQuery("select * from dir_nomenclature order by "+order_).addEntity(DirNomenclature.class)
				.setFirstResult((int) (p*itemOnPage-itemOnPage))
				.setMaxResults((int) itemOnPage)
				.list();
			
		
		return result;
	}

	@Override
	public List<DirNomenclature> getNomenclatureList() {
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("select * from dir_nomenclature order by name").addEntity(DirNomenclature.class).list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<DirNomenclGroup> getNomenclGroupList() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from DirNomenclGroup order by name").list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<DirNomenclGroupRoot> getNomenclGroupRootList() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from DirNomenclGroupRoot order by name").list();
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
		return  getSession().createQuery("from DirGender order by name").list();
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
	public void addTails(Collection<Tail> tails) {
		// TODO Auto-generated method stub
		Session sess = getSession();
		sess.createSQLQuery("delete from diff_of_tails").executeUpdate();
		TreeSet<NewTails> nTails = new TreeSet<NewTails>(); // для уникальных записей
		
		for(Tail tail: tails)
		{
			sess.save(tail);
			
			if(tail.getIsNew() != null)
			{
				NewTails nt = new NewTails(tail.getIsNew() , tail.getDirNomenclature().getId());
				nTails.add(nt);
			}
		}
		
		for(NewTails ntail: nTails)
		{
			sess.save(ntail);
		}
			
		sess.flush();
		sess.clear();
		
		System.gc();
	}


	@Override
	public List<Tail> getTailsList() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Tail.class).add(Restrictions.isNull("destruction_date")).addOrder(Order.asc("id")).list();
	}
	
	@Override
	public Object[] getTailsList(int p, long itemOnPage, String sortby) {


		Sortby sortby_enum = Sortby.id;
		try {
			sortby_enum = Sortby.valueOf(sortby);
		}
		catch(Exception exc) {
			sortby_enum = Sortby.id;
		}
		
		//Order order = null;
		String order_ = sortby_enum.name(); 
		switch(sortby_enum) {
			/*
			case name:
				//order = Order.asc(sortby);
				order_ = sortby_enum.name();
			break;
			case code:
				//order = Order.asc(sortby);
				order_ = sortby_enum.name();
			break;
			*/
				
		}
		
		long count = (long) getSession().createCriteria(Tail.class).add(Restrictions.isNull("destruction_date")).setProjection(Projections.rowCount()).uniqueResult();
		List<Tail> tailList = getSession().createCriteria(Tail.class).add(Restrictions.isNull("destruction_date")).addOrder(Order.asc(order_))
				.setFirstResult((int) (p*itemOnPage-itemOnPage))
				.setMaxResults((int) itemOnPage)
				.list();
		
		Object[] result = {count,tailList};
		return result;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getStoreList() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Store").list();
	}

	@Override
	public void addStore(Store store) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(store);
	}

	@Override
	public Store getStoreBySerVerUID(Long serialVersionUID) {
		// TODO Auto-generated method stub
		return (Store) getSession().createQuery("from Store s where s.serialVersionUID = :serialVersionUID").setParameter("serialVersionUID", serialVersionUID).uniqueResult();
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
	public void addOrder(ttadm.model.Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public ttadm.model.Order getOrder(Long order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ttadm.model.Order> getOrdersList() {
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
		return getSession().get(clazz, id);
	}

	@Override
	public void delObject(Object obj) {
		// TODO Auto-generated method stub
		getSession().delete(obj);
	}

	@Override
	public void updateTails() {
		// TODO Auto-generated method stub
		getSession().createSQLQuery("update tails set destruction_date = now() where destruction_date is null").executeUpdate();
	}

	@Override
	public User findByUserName(String username) {
		List<User> users = new ArrayList<User>();
		
		users = getSession()
				.createQuery("from User where name = :name")
				.setParameter("name", username)
				.list();
		
		if(users.size() > 0)
			return users.get(0);
		else
			return null;
	}


	@Override
	public void saveIModel(IModel imodel) {

		if(imodel instanceof DirNomenclature)
		{
				DirNomenclature	dirNomenclature = (DirNomenclature)imodel;		
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//Во избежании ERROR: duplicate key value violates unique constraint "dir_nomenclature_code_name_key"
				//Уникальные поля code, name
				//ищем существующюю запись
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				DirNomenclature dN_old = new DirNomenclature();
				dN_old.setCode(dirNomenclature.getCode());
				dN_old.setName(dirNomenclature.getName());
				
				dN_old = (DirNomenclature) getSession().createCriteria(DirNomenclature.class).add(Example.create(dN_old)).uniqueResult();
				
				try {
					dN_old.setArticle(dirNomenclature.getArticle());
					dN_old.setComposition(dirNomenclature.getComposition());
					dN_old.setModel(dirNomenclature.getModel());
					dN_old.setDirGender(dirNomenclature.getDirGender());
					
					dN_old.setAccess_date( new Timestamp(new java.util.Date().getTime() )); // Обновляем дату последней загрузки этой номенклатуры
		
					getSession().saveOrUpdate(dN_old);
				}
				catch(java.lang.NullPointerException nexc)
				{
					getSession().saveOrUpdate(dirNomenclature);
					
				}
		}
		else
				getSession().saveOrUpdate(imodel);
	}



	@Override
	public void callProcedure(String nameProc) {
		// TODO Auto-generated method stub
		getSession().getNamedQuery(nameProc);
	}





}
