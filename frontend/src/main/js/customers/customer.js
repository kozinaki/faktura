import React from 'react';
import client from '../client';
import UpdateDialog from "./update";
import CreateDialog from "./create";

class CustomerApp extends React.Component {
	constructor(props) {
		super(props);
		this.state = {customers: []};
		this.onCreate = this.onCreate.bind(this);
		this.onDelete = this.onDelete.bind(this);
		this.onUpdate = this.onUpdate.bind(this);
	}

	onCreate(newCustomer) {
		client({method: 'POST', path: '/api/v1/customers', entity: newCustomer, headers: {'Content-Type': 'application/json'}})
		.then(response => {
			newCustomer['id'] = response.entity.id;
			const customers = this.state.customers;
			customers.push(newCustomer);
			this.setState({
				customers: customers,
				attributes: this.state.attributes
			});
		})
	}

	onDelete(deleteCustomer) {
		const customerId = {};
		customerId['id'] = deleteCustomer.id;
		client({method: 'DELETE', path: '/api/v1/customers', entity: customerId, headers: {'Content-Type': 'application/json'}})
		.then(response => {
			const customers = this.state.customers.filter(customer => customer.id != deleteCustomer.id);
			this.setState({
				customers: customers,
				attributes: this.state.attributes
			});
		});
	}

	onUpdate(customer, updatedCustomer) {
		client({
			method: 'PUT',
			path: '/api/v1/customers',
			entity: updatedCustomer,
			headers: {'Content-Type': 'application/json'}
		}).then(response => {
			const customers = this.state.customers;

			const index_of_customer = customers.indexOf(customer);
			if (index_of_customer != -1) {
				customers[index_of_customer] = updatedCustomer;
			}
			this.setState({
				customers: customers,
				attributes: this.state.attributes
			});
			if (response.status.code === 412) {
				alert('DENIED: Unable to update ' + customer + '. Your copy is stale.');
			}
		});
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/v1/customers', headers: {'Accept': 'application/json'}})
		.then(response => {
			this.customers = response.entity;
			return client({method: 'GET', path: '/api/v1/customers/schema', headers: {'Accept': 'application/json'}});
		}).then(response => {
			const properties = response.entity.properties.filter(property => property != 'id');
			this.setState({
				customers: this.customers,
				attributes: properties
			});
		})
	}

	render() {
		return (
		  	<div>
		    	<CreateDialog 
					attributes={this.state.attributes} 
					onCreate={this.onCreate} />
				<CustomerList 
					customers={this.state.customers}
					attributes={this.state.attributes}
					onDelete={this.onDelete}
					onUpdate={this.onUpdate} />
			</div>
		)
	}
}

class CustomerList extends React.Component{
	render() {
		const customers = this.props.customers.map(customer =>
			<Customer key={customer.id} 
				customer={customer}
				attributes={this.props.attributes}
				onDelete={this.props.onDelete}
				onUpdate={this.props.onUpdate}/>
		);

		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
						<th>Phone</th>
						<th>Address</th>
						<th>Email</th>
            			<th>INN</th>
						<th>Description</th>
					</tr>
					{customers}
				</tbody>
			</table>
		)
	}
}

class Customer extends React.Component{

	constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
	}

	handleDelete() {
		this.props.onDelete(this.props.customer);
	}

	render() {
		return (
			<tr>
				<td>{this.props.customer.name}</td>
				<td>{this.props.customer.phone}</td>
				<td>{this.props.customer.address}</td>
				<td>{this.props.customer.email}</td>
				<td>{this.props.customer.inn}</td>
				<td>{this.props.customer.description}</td>
				<td>
					<UpdateDialog customer={this.props.customer}
									attributes={this.props.attributes}
									onUpdate={this.props.onUpdate}/>
					<div id="two_buttons">
						<button onClick={this.handleDelete}>Delete</button>
					</div>
        		</td>
			</tr>
		)
	}
}

export {Customer, CustomerList, CustomerApp};