package com.incra

/**
 * The <i>ResourceService</i> supports the use of Resources and their ResourcePrices.
 * For instance, it can find the price of a Resource at a specific date, or all 
 * candidate Partners and Vendors for a given Resource. The ResourceService also handles 
 * ResourcePrice entities.
 * 
 * @author Jeffrey Risberg
 * @since 11/20/10
 */
class ResourceService {
	
	/**
	 * Return the price of a specified resource from the specified provider
	 */
	double getPrice(Provider provider, Resource resource) {				
		def criteria = ResourcePrice.createCriteria();
		
		def query = {
			and {
				eq('provider', provider)
				eq('resource', resource)
			}
		}
		
		List<ResourcePrice> resourcePrices = criteria.list(query)
		log.info "getCost " + resourcePrices
		
		if (resourcePrices) {
			return resourcePrices[0].price
		}
		0.0
	}
	
	ResourcePrice saveResourcePrice(Provider provider, Resource resource, Date validFromDate, double price){
		ResourcePrice resourcePrice
		resourcePrice = ResourcePrice.findByProviderAndResource(provider, resource)
		if(resourcePrice){
			throw new RuntimeException("Resource Price from provider ($provider) for resource ($resource) already exists" );
		}else{
		resourcePrice = new ResourcePrice(provider: provider, resource: resource, validFromDate: validFromDate, price: price)
		resourcePrice.save()
		}
		resourcePrice
	}
}
