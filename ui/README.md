## Entanto Survey UI - Widgets

## Building
* First run an `yarn` to downlaod all dependencies
* Then run `yarn build` 

The files will be generated on `build/` folder.

## Widgets

### Survey
This widget will render a single survey to be submitted by any logged user.
The surveys can only be submitted once per user.

#### Tag Name
`<en-survey />`

#### Properties
>- `service-url`: The URL for the survey microservice. Example: `https://entando.com/survey'
>- `survey-id`: The ID of the survey to be rendered.

#### Usage
```xml
<en-survey service-url="{{insert service url}}" 
           survey-id="{{ inser survey id }}" />
```

### Survey Admin
The admin interface has:

* List Surveys
* Create a New survey with the survey designer
* Check survey details, including survey submissions

#### Tag Name
`<en-survey-admin />`

#### Properties
>- `service-url`: The URL for the survey microservice. Example: `https://entando.com/survey'

#### Usage
```xml
<en-survey-admin service-url="{{insert service url}}" />
```