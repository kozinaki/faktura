import React from 'react';
import client from '../client';

class PaymentApp extends React.Component {
	constructor(props) {
		super(props);
		this.state = {payments: []};
		this.onCreate = this.onCreate.bind(this);
		this.onDelete = this.onDelete.bind(this);
		this.onUpdate = this.onUpdate.bind(this);
	}

	onCreate(newPayment) {
		client({method: 'POST', path: '/api/v1/payments', entity: newPayment, headers: {'Content-Type': 'application/json'}})
		.then(response => {
			newPayment['id'] = response.entity.id;
			const payments = this.state.payments;
			payments.push(newPayment);
			this.setState({
				payments: payments,
				attributes: this.state.attributes
			});
		})
	}

	onDelete(deletePayment) {
		const paymentId = {};
		paymentId['id'] = deletePayment.id;
		client({method: 'DELETE', path: '/api/v1/payments', entity: paymentId, headers: {'Content-Type': 'application/json'}})
		.then(response => {
			const payments = this.state.payments.filter(payment => payment.id != deletePayment.id);
			this.setState({
				payments: payments,
				attributes: this.state.attributes
			});
		});
	}

	onUpdate(payment, updatedPayment) {
		client({
			method: 'PUT',
			path: '/api/v1/payments',
			entity: updatedPayment,
			headers: {'Content-Type': 'application/json'}
		}).then(response => {
			const payments = this.state.payments;

			const index_of_payment = payments.indexOf(payment);
			if (index_of_payment != -1) {
				payments[index_of_payment] = updatedPayment;
			}
			this.setState({
				payments: payments,
				attributes: this.state.attributes
			});
			if (response.status.code === 412) {
				alert('DENIED: Unable to update ' + payment + '. Your copy is stale.');
			}
		});
	}

	// componentDidMount() {
	// 	client({method: 'GET', path: '/api/v1/payments', headers: {'Accept': 'application/json'}})
	// 	.then(response => {
	// 		this.payments = response.entity;
	// 		return client({method: 'GET', path: '/api/v1/payments/schema', headers: {'Accept': 'application/json'}});
	// 	}).then(response => {
	// 		const properties = response.entity.properties.filter(property => property != 'id');
	// 		this.setState({
	// 			payments: this.payments,
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

export {PaymentApp};