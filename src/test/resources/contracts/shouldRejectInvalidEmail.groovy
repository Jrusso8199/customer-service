import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should reject customer with invalid email"
    
    request {
        method 'POST'
        url '/api/customers'
        body([
            firstName: "John",
            lastName: "Doe",
            email: "invalid-email",
            password: "password123"
        ])
        headers {
            contentType('application/json')
        }
    }
    
    response {
        status 400
    }
}
