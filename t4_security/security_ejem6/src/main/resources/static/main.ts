import {bootstrap} from 'angular2/platform/browser';
import {AppComponent} from './app/app.component';
import {provide} from 'angular2/core';
import {RouteConfig,  ROUTER_DIRECTIVES, ROUTER_PROVIDERS,
        LocationStrategy, HashLocationStrategy} from 'angular2/router';
import {BrowserXhr} from 'angular2/http';
import {CustomBrowserXhr} from './app/multipart-upload/custom-browser-xhr';

bootstrap(AppComponent, [
  ROUTER_PROVIDERS, 
  provide(LocationStrategy, {useClass: HashLocationStrategy})
  /*provide(BrowserXhr, { useClass: CustomBrowserXhr })*/
]);