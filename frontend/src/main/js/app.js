import React from 'react';
import ReactDOM from 'react-dom';
import {CustomerApp} from './customers/customer'
import {OrderApp} from './orders/order'
import {PaymentApp} from './payments/payment'
import {
	BrowserRouter as Router,
	Route,
	Routes,
	Link
} from "react-router-dom";

// class App extends React.Component {
// 	render() {
// 		return (
// 			<div className="app">
// 				<Routes> 
// 					<HomeLayoutRoute path="/" element={<Home />} />
// 					<PrivateRoute path="/" element={<PrivateScreen/>} />
// 					<Route path="customers" element={<CustomerApp />} />
// 					<Route path="orders" element={<OrderApp />} />
// 					<Route path="payments" element={<PaymentApp />} />
// 				</Routes>
// 			</div>
// 		);
// 	}
// }

class HomeApp extends React.Component {
	render() {
		return (
			<div className="welcome">Welcome to faktura</div>
		)
	}
}

export default function App() {
	return (
		<Router>
			<div>
				<div className="container">
					<div className="row">
						<div className="column">
							<Link style={{fontSize: "36px"}} to="/">Home</Link>
						</div>
						<div className="column">
							<Link style={{fontSize: "36px"}} to="/customers">Customers</Link>
						</div>
						<div className="column">
							<Link style={{fontSize: "36px"}} to="/orders">Orders</Link>
						</div>
						<div className="column">
							<Link style={{fontSize: "36px"}} to="/payments">Payments</Link>
						</div>
					</div>
				</div>


				<Routes>
					<Route path="/" element={<HomeApp />} />
					<Route path="customers" element={<CustomerApp />} />
					<Route path="orders" element={<OrderApp />} />
					<Route path="payments" element={<PaymentApp />} />
				</Routes>
			</div>
		</Router>
	);
  }

ReactDOM.render(<App />, document.getElementById('faktura'))