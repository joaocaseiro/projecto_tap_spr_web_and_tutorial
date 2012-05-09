package com.grupoatwork.celebrity.pages;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.grupoatwork.celebrity.dao.AddressDao;
import com.grupoatwork.celebrity.dao.CelebrityDao;
import com.grupoatwork.celebrity.dao.CountyDao;
import com.grupoatwork.celebrity.dao.DistrictDao;
import com.grupoatwork.celebrity.dao.MovieDao;
import com.grupoatwork.celebrity.dao.ParishDao;
import com.grupoatwork.celebrity.dao.RoleDao;
import com.grupoatwork.celebrity.entities.Address;
import com.grupoatwork.celebrity.entities.BaseDomain;
import com.grupoatwork.celebrity.entities.Celebrity;
import com.grupoatwork.celebrity.entities.County;
import com.grupoatwork.celebrity.entities.District;
import com.grupoatwork.celebrity.entities.Movie;
import com.grupoatwork.celebrity.entities.Parish;
import com.grupoatwork.celebrity.entities.Role;
import com.grupoatwork.celebrity.util.AddressFileReader;
import com.grupoatwork.celebrity.util.AddressHandler;
import com.grupoatwork.celebrity.util.CelebrityHandler;
import com.grupoatwork.celebrity.util.CountyHandler;
import com.grupoatwork.celebrity.util.DistrictHandler;
import com.grupoatwork.celebrity.util.ParishHandler;

public class Index {

	@Inject
	private CelebrityDao celebrityDao;
	@Inject
	private DistrictDao districtDao;
	@Inject
	private CountyDao countyDao;
	@Inject
	private ParishDao parishDao;
	@Inject
	private AddressDao addressDao;
	@Inject
	private MovieDao movieDao;
	@Inject
	private RoleDao roleDao;
	
	@InjectPage
	private DetailsDistrict detailsDistrictPage;
	@InjectPage
	private DetailsCelebrity detailsCelebrityPage;
	@InjectPage
	private DetailsRole detailsRolePage;

	@Inject
	@Path("context:resources/celebrities.txt")
	private Asset fileCelebrities;

	@Inject
	@Path("context:resources/distritos.txt")
	private Asset fileDistritos;

	@Inject
	@Path("context:resources/concelhos.txt")
	private Asset fileConcelhos;

	@Inject
	@Path("context:resources/freguesias.txt")
	private Asset fileFreguesias;

	@Inject
	@Path("context:resources/todos.txt")
	private Asset fileTodos;

	private String title = "Index Page for Tapestry";

	private Celebrity celebrity;
	private District district;
	private County county;
	private Parish parish;
	private Address address;
	private Role role;

	void onActivate() {
		initAndLoad();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<Celebrity> getCelebrities() {
		List<Celebrity> list = celebrityDao.getCelebrities();
		return list;
	}

	public List<District> getDistricts() {
		List<District> list = districtDao.getDistricts();
		return list;
	}

	public List<Movie> getMovies() {
		List<Movie> list = movieDao.getMovies();
		return list;
	}

	public List<County> getCounties() {
		List<County> list = countyDao.getCounties();
		return list;
	}

	public List<Parish> getParishes() {
		List<Parish> list = parishDao.getParishes();
		return list;
	}

	public List<Address> getAddresses() {
		List<Address> list = addressDao.getAddresses();
		return list;
	}
	
	public List<Role> getRoles() {
		List<Role> list = roleDao.getRoles();
		return list;
	}

	public void initAndLoad() {
		loadCelebrities();
		loadDistricts();
		loadCounties();
		loadParishes();
		// loadAddresses();
	}

	private void loadAddresses() {
		List<Address> lists = getAddresses();
		if (lists != null && lists.size() == 0) {
			InputStream stream;
			try {
				stream = fileTodos.getResource().openStream();
				if (stream != null) {
					AddressHandler handler = new AddressHandler();
					handler.setParishes(parishDao);
					handler.setCounties(countyDao);
					handler.setDistricts(districtDao);
					List<BaseDomain> addresses = AddressFileReader.reader(
							stream, handler);

					Address address = null;
					for (int i = 0; i < addresses.size(); i++) {
						address = (Address) addresses.get(i);
						address.setEntityVersion(new Long(1));
						System.out.println(address.toString());
						addressDao.save((Address) address);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadParishes() {
		List<Parish> lists = getParishes();
		if (lists != null && lists.size() == 0) {
			InputStream stream;
			try {
				stream = fileFreguesias.getResource().openStream();
				if (stream != null) {
					ParishHandler handler = new ParishHandler();
					handler.setCounties(countyDao);
					handler.setDistricts(districtDao);
					List<BaseDomain> parishes = AddressFileReader.reader(
							stream, handler);

					Parish parish = null;
					for (int i = 0; i < parishes.size(); i++) {
						parish = (Parish) parishes.get(i);
						parish.setEntityVersion(new Long(1));
						System.out.println(parish.toString());
						parishDao.save((Parish) parish);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadCounties() {
		List<County> lists = getCounties();
		if (lists != null && lists.size() == 0) {
			InputStream stream;
			try {
				stream = fileConcelhos.getResource().openStream();
				if (stream != null) {
					CountyHandler handler = new CountyHandler();
					handler.setDistricts(districtDao);
					List<BaseDomain> counties = AddressFileReader.reader(
							stream, handler);

					County county = null;
					for (int i = 0; i < counties.size(); i++) {
						county = (County) counties.get(i);
						county.setEntityVersion(new Long(1));
						System.out.println(county.toString());
						countyDao.save((County) county);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadCelebrities() {
		List<Celebrity> lists = getCelebrities();
		if (lists != null && lists.size() == 0) {
			InputStream stream;
			try {
				stream = fileCelebrities.getResource().openStream();
				if (stream != null) {
					CelebrityHandler handler = new CelebrityHandler();
					List<BaseDomain> celebrities = AddressFileReader.reader(
							stream, handler);

					Celebrity celebrity = null;
					for (int i = 0; i < celebrities.size(); i++) {
						celebrity = (Celebrity) celebrities.get(i);
						celebrity.setEntityVersion(new Long(1));
						System.out.println(celebrity.toString());
						celebrityDao.save((Celebrity) celebrity);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadDistricts() {
		List<District> lists = getDistricts();
		if (lists != null && lists.size() == 0) {
			InputStream stream;
			try {
				stream = fileDistritos.getResource().openStream();
				if (stream != null) {
					DistrictHandler handler = new DistrictHandler();
					List<BaseDomain> districts = AddressFileReader.reader(
							stream, handler);

					District district = null;
					for (int i = 0; i < districts.size(); i++) {
						district = (District) districts.get(i);
						district.setEntityVersion(new Long(1));
						System.out.println(district.toString());
						districtDao.save((District) district);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public Parish getParish() {
		return parish;
	}

	public void setParish(Parish parish) {
		this.parish = parish;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@OnEvent(component="detailsLink")
	Object onShowDetails(long id) {
		District district = districtDao.read(id);
		detailsDistrictPage.setDistrict(district);
		return detailsDistrictPage;
	}
	@OnEvent(component="detailsFirstCelebrityLink")
	Object onShowCelebrityFirstDetails(long id) {
		Celebrity celebrity = celebrityDao.read(id);
		detailsCelebrityPage.setCelebrity(celebrity);
		return detailsCelebrityPage;
	}
	@OnEvent(component="detailsLastCelebrityLink")
	Object onShowCelebritySecondDetails(long id) {
		Celebrity celebrity = celebrityDao.read(id);
		detailsCelebrityPage.setCelebrity(celebrity);
		return detailsCelebrityPage;
	}
	@OnEvent(component="detailsRoleLink")
	Object onShowRoleDetails(long id) {
		Role role = roleDao.read(id);
		detailsRolePage.setRole(role);
		return detailsRolePage;
	}
	
	public String getRoleCelebrityName(Celebrity celebrity) {
		return celebrity.getFirstName() + celebrity.getLastName();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
