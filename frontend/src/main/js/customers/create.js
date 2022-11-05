import React from 'react';
import ReactDOM from'react-dom';

class CreateDialog extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		const newCustomer = {};
		this.props.attributes.forEach(attribute => {
			newCustomer[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onCreate(newCustomer);

		// clear out the dialog's inputs
		this.props.attributes.forEach(attribute => {
			ReactDOM.findDOMNode(this.refs[attribute]).value = '';
		});

		// Navigate away from the dialog to hide it.
		window.location = "#";
	}

	render() {
		const inputs = this.props.attributes?.map(attribute => {
			if (attribute == 'description' || attribute == 'address') {
				return <p key={attribute}>
					<textarea name={attribute} placeholder={attribute} ref={attribute} cols="10" rows="1" required></textarea>
				</p>
			} else {
				return <p key={attribute}>
						<input type="text" placeholder={attribute} ref={attribute} className="field" required/>
					</p>
			}
		});

		return (
			<div>
				<a className="button" href="#createCustomer">Create customer</a>

				<div id="createCustomer" className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Create new customer</h2>

						<form onSubmit={this.handleSubmit}>
							<p key="name">
                <input type="text" placeholder="name" ref="name" className="field" required/>
              </p>
              <p key="phone">
                <input type="text" placeholder="phone" ref="phone" className="field" required/>
              </p>
              <p key="address">
                <textarea name="address" placeholder="address" ref="address" cols="10" rows="1" required></textarea>
              </p>
              <p key="email">
                <input type="text" placeholder="email" ref="email" className="field" required/>
              </p>
              <p key="inn">
                <input type="text" placeholder="inn" ref="inn" className="field"/>
              </p>
              <p key="description">
                <textarea name="description" placeholder="description" ref="description" cols="10" rows="1"></textarea>
              </p>
							<input type="submit" value="Create"/>
						</form>
					</div>
				</div>
			</div>
		)
	}
}

export default CreateDialog;