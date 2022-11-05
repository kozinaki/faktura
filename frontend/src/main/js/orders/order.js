import React from 'react';
import client from '../client';

class OrderApp extends React.Component {
	constructor(props) {
		super(props);
		this.state = {orders: []};
		this.onCreate = this.onCreate.bind(this);
		this.onDelete = this.onDelete.bind(this);
		this.onUpdate = this.onUpdate.bind(this);
	}

	onCreate(newOrder) {
		client({method: 'POST', path: '/api/v1/orders', entity: newOrder, headers: {'Content-Type': 'application/json'}})
		.then(response => {
			newOrder['id'] = response.entity.id;
			const orders = this.state.orders;
			orders.push(newOrder);
			this.setState({
				orders: orders,
				attributes: this.state.attributes
			});
		})
	}

	onDelete(deleteOrder) {
		const orderId = {};
		orderId['id'] = deleteOrder.id;
		client({method: 'DELETE', path: '/api/v1/orders', entity: orderId, headers: {'Content-Type': 'application/json'}})
		.then(response => {
			const orders = this.state.orders.filter(order => order.id != deleteOrder.id);
			this.setState({
				orders: orders,
				attributes: this.state.attributes
			});
		});
	}

	onUpdate(order, updatedOrder) {
		client({
			method: 'PUT',
			path: '/api/v1/orders',
			entity: updatedOrder,
			headers: {'Content-Type': 'application/json'}
		}).then(response => {
			const orders = this.state.orders;
			const index_of_order = orders.indexOf(order);
			if (index_of_order != -1) {
				orders[index_of_order] = updatedOrder;
			}
			this.setState({
				orders: orders,
				attributes: this.state.attributes
			});
			if (response.status.code === 412) {
				alert('DENIED: Unable to update ' + order + '. Your copy is stale.');
			}
		});
	}

	// componentDidMount() {
	// 	client({method: 'GET', path: '/api/v1/orders', headers: {'Accept': 'application/json'}})
	// 	.then(response => {
	// 		this.orders = response.entity;
	// 		return client({method: 'GET', path: '/api/v1/orders/schema', headers: {'Accept': 'application/json'}});
	// 	}).then(response => {
	// 		const properties = response.entity.properties.filter(property => property != 'id');
	// 		this.setState({
	// 			orders: this.orders,
	// 			attributes: properties
	// 		});
	// 	})
	// }

	render() {
		return (
		  	<div>
				<div className="welcome">in dev</div>
			</div>
		)
	}
}

export {OrderApp};