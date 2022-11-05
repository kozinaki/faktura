import React from 'react';
import ReactDOM from'react-dom';

class UpdateDialog extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		const updatedCustomer = {};
		this.props.attributes.forEach(attribute => {
			updatedCustomer[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		updatedCustomer['id'] = this.props.customer['id'];
		this.props.onUpdate(this.props.customer, updatedCustomer);
		window.location = "#";
	}

	render() {
		const inputs = this.props.attributes.map(attribute =>
			<p key={attribute + this.props.customer[attribute]}>
				<input type="text" placeholder={attribute}
					   defaultValue={this.props.customer[attribute]}
					   ref={attribute} className="field"/>
			</p>
		);

		const dialogId = "updateCustomer-" + this.props.customer['id'];

		return (
			<div id="two_buttons" key={this.props.customer['id']}>
				<a className="button" href={"#" + dialogId}>Update</a>&emsp;
				<div id={dialogId} className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Update an customer</h2>

						<form>
							<p key="name">
                <input type="text" defaultValue={this.props.customer['name']} placeholder="name" ref="name" className="field" required/>
              </p>
              <p key="phone">
                <input type="text" defaultValue={this.props.customer['phone']} placeholder="phone" ref="phone" className="field" required/>
              </p>
              <p key="address">
                <textarea name="address" defaultValue={this.props.customer['address']} placeholder="address" ref="address" cols="10" rows="1" required></textarea>
              </p>
              <p key="email">
                <input type="text" defaultValue={this.props.customer['email']} placeholder="email" ref="email" className="field" required/>
              </p>
              <p key="inn">
                <input type="text" defaultValue={this.props.customer['inn']} placeholder="inn" ref="inn" className="field"/>
              </p>
              <p key="description">
                <textarea name="description" defaultValue={this.props.customer['description']} placeholder="description" ref="description" cols="10" rows="1"></textarea>
              </p>
							<button onClick={this.handleSubmit}>Update</button>
						</form>
					</div>
				</div>
			</div>
		)
	}
};

export default UpdateDialog;