package com.rt.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Dao.CustomerDao;
import com.rt.Entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerdao;

	public boolean addcustomer(Customer customer) {
		return customerdao.addcustomer(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerdao.getAllCustomers();
	}

	public Customer getCustomerById(int customerId) {
		return customerdao.getCustomerById(customerId);
	}

	public boolean modifyCustomer(Customer customer) {
		return customerdao.modifyCustomer(customer);
	}

	public boolean softDeleteCustomer(int customerId) {
		return customerdao.softDeleteCustomer(customerId);
	}

	public List<Customer> getAllCustomer(Integer userId) {
		return customerdao.getAllCustomer(userId);
	}

	public List<Integer> getCustomerIdsByUserId(Integer userId) {
		return customerdao.getCustomerIdsByUserId(userId);
	}
}
