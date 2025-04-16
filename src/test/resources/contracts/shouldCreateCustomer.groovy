import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should create a customer"
    
    request {
        method 'POST'
        url '/api/customers'
        body([
            firstName: "John",
            lastName: "Doe",
            email: "john.doe@example.com",
            password: "password123"
        ])
        headers {
            contentType('application/json')
        }
    }
    
    response {
        status 200
        body([
            id: 1,
            firstName: "John",
            lastName: "Doe",
            email: "john.doe@example.com",
            password: "password123"
        ])
        headers {
            contentType('application/json')
        }
    }
}
