package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return valid as true if valid date is input")
    request {
        method('GET')
        urlPath('/provider/validDate') {
            queryParameters {
                parameter("date", $(consumer(isoDate()), producer('2001-02-03')))
            }
        }
        headers {
            contentType('application/json')
        }
    }
    response {
        status(200)
        headers {
            contentType('application/json')
        }
        body(
                givenDate: $(consumer('2001-02-03'), producer(isoDate())),
                year: $(consumer('2001'), producer(regex("(190[0-9]|19[5-9]\\d|200\\d|2020)"))),
                month: $(consumer('2'), producer(regex("(1[0-2]|[1-9])"))),
                day: $(consumer('3'), producer(regex("(3[01]|[12][0-9]|[1-9])"))),
                isValidDate: true,
                message: "date parsed successfully"
        )
    }
}