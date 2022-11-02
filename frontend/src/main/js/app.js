const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {
	constructor(props) {
		super(props);
		this.state = {customers: []};
		this.onCreate = this.onCreate.bind(this);
		this.onDelete = this.onDelete.bind(this);
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
    customerId = {};
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
		    <CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
			  <CustomerList customers={this.state.customers} onDelete={this.onDelete}/>
			</div>
		)
	}
}

class CustomerList extends React.Component{
	render() {
		const customers = this.props.customers.map(customer =>
			<Customer key={customer.id} customer={customer} onDelete={this.props.onDelete}/>
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
				  <button onClick={this.handleUpdate}>Update</button>&emsp;
          <button onClick={this.handleDelete}>Delete</button>
        </td>
			</tr>
		)
	}
}

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
		  }
		);

		return (
			<div>
			  <h1>Customers</h1>
				<a className="button" href="#createCustomer">Create customer</a>

				<div id="createCustomer" className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Create new customer</h2>

						<form onSubmit={this.handleSubmit}>
							{inputs}
							<input type="submit" value="Create"/>
						</form>
					</div>
				</div>
			</div>
		)
	}

}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)