from flask import Flask, render_template, request, redirect, url_for
from datetime import datetime
import requests
import json
import ast

app = Flask(__name__)

@app.route('/')
def home():
    return render_template('home.html')

@app.route('/add_user', methods=['GET', 'POST'])
def add_user():
     if request.method == 'POST':
        global username1
        username1 = request.form['username']
        password = request.form['password']
        email = request.form['email']

        user_data = {
            'username' : username1,
            'password' : password,
            'email' : email
        }

        user_json = json.dumps(user_data)

        url = 'http://localhost:8085/add-user'
        headers = {'Content-Type': 'application/json'}
        response = requests.post(url, headers=headers, data=user_json)

        print(response.text) 

        if response.status_code == 200:

            if response.text == 'USER ADDED SUCCESSFULY':
                return render_template('choose_flight.html')
            else:
                return render_template('home.html', ERROR="Username already exists")
       
        return render_template('home.html')

@app.route('/users')
def users():
    response = requests.get('http://localhost:8085/users')
    users = response.json()
    return render_template('info_result.html', users=users)

# Login page route
@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        global username1
        username1 = request.form['username']
        password = request.form['password']
        
        # Make a REST call to check login credentials
        url = f'http://localhost:8085/check-user/{username1}/{password}'
        response = requests.get(url)
        print(response)
        if response.status_code == 200:
            result = response.json()
            print(result)
            if result == True:
                return render_template('choose_flight.html')
            else:
                return render_template('home.html', error="Invalid username or password")
        else:
            return render_template('home.html')

@app.route('/logout')
def logout():
    global username1
    username1 = ""
    return render_template('home.html')

# Choose flight page route
@app.route('/choose-flight', methods=['GET', 'POST'])
def choose_flight():

    if request.method == 'POST':

        departure_airport = request.form['departure_airport']
        arrival_airport = request.form['arrival_airport']
        departure_date = request.form['departure_date']

        url = f'http://localhost:8085/flights/{departure_airport}/{arrival_airport}/{departure_date}'

        response = requests.get(url)
        result = response.json()
        global flight_list
        flight_list = result

        return render_template('flight_list.html', result=result)

def get_flight_details(flyFrom, flyTo, flight_no, price, departure_date):

    # Find the flight with the matching criteria
    for flight in flight_list:
        if flight["flyFrom"] == flyFrom and flight["flyTo"] == flyTo and flight["flightNo"] == flight_no and flight["price"] == price and flight["localDeparture"] == departure_date:
            return flight
    # Return None if flight is not found
    return None

@app.route('/flight_description/<flyFrom>/<flyTo>/<int:flight_no>/<int:price>/<departure_date>/<int:available_seats>', methods=['GET'])
def flight_description(flyFrom, flyTo, flight_no, price, departure_date, available_seats):
    
    input_datetime = datetime.strptime(departure_date, '%Y-%m-%d  %H:%M')
    output_string = input_datetime.strftime('%Y-%m-%d')

    flight = get_flight_details(flyFrom, flyTo, flight_no, price, departure_date)

    url = f'http://localhost:8085/get-seating/{flyFrom}/{flyTo}/{output_string}/{flight_no}/{price}/{available_seats}'     
    response2 = requests.get(url)

    global departure_airport
    departure_airport = flyFrom

    global arrival_airport
    arrival_airport = flyTo

    global date_departure
    date_departure = output_string
   
    global flight_number
    flight_number = flight_no

    global cost_per_ticket
    cost_per_ticket = price

    if response2.status_code == 200:         
        try:             
            result2 = json.loads(response2.content)   
            print(result2)        
            # Ensure result2 is a 2D array             
            if isinstance(result2, list) and all(isinstance(row, list) for row in result2):                 
                return render_template('booking.html', result=flight, result2=result2)             
            else:                 
                # Handle invalid result2 format                 
                return render_template('error1.html', message="Invalid seating information")         
        except json.JSONDecodeError:             
            # Handle JSON decoding error             
            return render_template('error2.html', message="Failed to decode seating information")     
    else:         
        # Handle failed request to get seating information         
        return render_template('error3.html', message="Failed to retrieve seating information")

@app.route('/route_payment', methods=['GET', 'POST'])
def payment_intermediate():
    return render_template('payment_details.html')

@app.route('/payment_details', methods=['GET', 'POST'])

def make_payment():

    # selected_seats = request.form.getlist('seats[]')

    # num_seats = len(selected_seats)
 
    if request.method == 'POST':
        # username = request.form['username']
        accountNo = request.form['accountNo']
        cardHolderName = request.form['cardHolderName']
        cardNo = request.form['cardNo']
        cvv = request.form['cvv']
        expDate = request.form['expDate']
        selected_seats = request.form['selectedSeats']

        payment_data = {

            'accountNo': accountNo,
            'cardHolderName': cardHolderName,
            'cardNo': cardNo,
            'cvv': cvv,
            'expDate': expDate
        }
 
        seats_string = selected_seats
        seats_list = ast.literal_eval(seats_string)
        num_seats = len(seats_list)
        seats_combined = ",".join(seats_list)

        payment_json = json.dumps(payment_data)

        # Make a REST call to create a new transaction

        url = f'http://localhost:8085/new-order/{username1}/{num_seats}/{departure_airport}/{arrival_airport}/{date_departure}/{flight_number}/{seats_combined}/{cost_per_ticket}'

        headers = {'Content-Type': 'application/json'}
        response = requests.post(url, headers=headers, data=payment_json)

        url2 = f'http://localhost:8085/get-transaction/{username1}'
        response2 = requests.get(url2)
        transactions = []
        for transaction_str in response2.json():
            transaction_dict = {}
            for field_str in transaction_str.split(', '):
                field_name, field_value = field_str.split(':', maxsplit=1)
                transaction_dict[field_name.strip()] = field_value.strip()
            transactions.append(transaction_dict)

        return render_template('final.html', result=response.text, transactions=transactions)


if __name__ == '__main__':
    app.run(debug=True,port=8090,host='0.0.0.0')
