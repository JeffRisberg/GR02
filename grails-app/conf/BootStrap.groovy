import grails.util.Environment

import com.incra.Account
import com.incra.AccountLink
import com.incra.AccountService
import com.incra.AccountType
import com.incra.Address
import com.incra.Comment
import com.incra.Contact
import com.incra.ContentItem
import com.incra.ContentSection
import com.incra.EntityType
import com.incra.Flow
import com.incra.FlowService
import com.incra.GeoScale
import com.incra.Language
import com.incra.MenuItem
import com.incra.Module
import com.incra.PipelineFoot
import com.incra.Preferences
import com.incra.Product
import com.incra.ProductAttribute
import com.incra.ProductAttributeValue
import com.incra.ProductCategory
import com.incra.Profile
import com.incra.Project
import com.incra.ProjectService
import com.incra.ProjectStatus
import com.incra.ProjectType
import com.incra.Provider
import com.incra.ROIProject
import com.incra.Resource
import com.incra.ResourcePrice
import com.incra.ResourceService
import com.incra.ResourceType
import com.incra.Role
import com.incra.TagUsageService
import com.incra.TimeScale
import com.incra.TimeSegment
import com.incra.TimeZone
import com.incra.Transaction
import com.incra.TransactionCategory
import com.incra.TransactionService
import com.incra.TransactionType
import com.incra.UnitOfMeasure
import com.incra.User
import com.incra.UserRole
import com.incra.domain.ContentItemType

import grails.util.Environment

import com.incra.Account
import com.incra.AccountLink
import com.incra.AccountService
import com.incra.AccountType
import com.incra.Address
import com.incra.Comment
import com.incra.Contact
import com.incra.ContentItem
import com.incra.ContentQualifier
import com.incra.ContentSection
import com.incra.EntityType
import com.incra.Flow
import com.incra.FlowService
import com.incra.GeoScale
import com.incra.Language
import com.incra.MenuItem
import com.incra.Module
import com.incra.PipelineFoot
import com.incra.Preferences
import com.incra.Product
import com.incra.ProductAttribute
import com.incra.ProductAttributeValue
import com.incra.ProductCategory
import com.incra.Profile
import com.incra.Project
import com.incra.ProjectService
import com.incra.ProjectStatus
import com.incra.ProjectType
import com.incra.Provider
import com.incra.ROIProject
import com.incra.Resource
import com.incra.ResourcePrice
import com.incra.ResourceService
import com.incra.ResourceType
import com.incra.Role
import com.incra.TagUsageService
import com.incra.TimeScale
import com.incra.TimeSegment
import com.incra.TimeZone
import com.incra.Transaction
import com.incra.TransactionCategory
import com.incra.TransactionService
import com.incra.TransactionType
import com.incra.UnitOfMeasure
import com.incra.User
import com.incra.UserRole
import com.incra.domain.ContentItemType

/**
 * The <i>BootStrap</i> class has been extended to set up reference data 
 * (TimeZone, Language, UOM, a few sample users, some Transaction Categories and 
 * their Transactions.
 *
 * @author Jeffrey Risberg
 * @since 09/29/10
 */
class BootStrap {

  def fixtureLoader
  ProjectService projectService
  AccountService accountService
  FlowService flowService
  TagUsageService tagUsageService
  ResourceService resourceService
  TransactionService transactionService

  def init = { servletContext ->
    if (Language.count() == 0) fixtureLoader.load("languages")
    if (ProjectStatus.count() == 0) fixtureLoader.load("projectStatuses")
    if (GeoScale.count() == 0) fixtureLoader.load("geoScales")
    if (TimeZone.count() == 0) fixtureLoader.load("timeZones")
    if (TimeSegment.count() == 0) fixtureLoader.load("timeSegments")
    if (UnitOfMeasure.count() == 0) fixtureLoader.load("uoms")

    // next candidates to become fixtures
    createEntityTypesIfRequired()
    createResourceTypesIfRequired()
    createResourcesIfRequired()

    // maybe fixtures, maybe not
    createAccountTypesIfRequired()
    createTransactionCategoriesIfRequired()
    createProjectTypesIfRequired()
    createRolesIfRequired()
    createBasicUsersIfRequired()
    createPipelineFootsIfRequired()
    createProductsIfRequired()
    createContentQualifiersIfRequired()
    createContentItemsIfRequired()

    switch (Environment.current) {
      case Environment.DEVELOPMENT:
      case Environment.TEST:
        createProvidersIfRequired()
        createResourcePricesIfRequired()

        createSampleDataIfRequired()
        createTestProjectsIfRequired()
        break;

      case Environment.PRODUCTION:
        createProvidersIfRequired()
        createResourcePricesIfRequired()
        break;
    }
    createCustomersIfRequired()
    createComponentsIfRequired()
  }

  def destroy = {
  }

  void createEntityTypesIfRequired() {
    if (EntityType.count() == 0) {
      println "Fresh Database. Creating entity types."

      EntityType entityType

      entityType = new EntityType(name: "Account", plural: "Accounts",
          hasOwnership: true, hasGeography: true,
          supportsComments: true, supportsTags: true)
      entityType.save()

      entityType = new EntityType(name: "Transaction", plural: "Transactions",
          hasOwnership: true, hasGeography: false,
          supportsComments: false, supportsTags: false);
      entityType.save()

      entityType = new EntityType(name: "Project", plural: "Projects",
          hasOwnership: true, hasGeography: false,
          supportsComments: true, supportsTags: true)
      entityType.save()

      entityType = new EntityType(name: "Flow", plural: "Flows",
          hasOwnership: true)
      entityType.save()

      entityType = new EntityType(name: "Provider", plural: "Providers",
          hasOwnership: false, hasGeography: true,
          supportsComments: true, supportsTags: true)
      entityType.save()

      entityType = new EntityType(name: "Vendor", plural: "Vendors",
          hasOwnership: false, hasGeography: true,
          supportsComments: true, supportsTags: true)
      entityType.save()

      entityType = new EntityType(name: "Partner", plural: "Partners",
          hasOwnership: false, hasGeography: true,
          supportsComments: true, supportsTags: true)
      entityType.save()

      entityType = new EntityType(name: "ContentItem", plural: "ContentItems",
          hasOwnership: false, hasGeography: true,
          supportsComments: true, supportsTags: true)
      entityType.save()
    }
    else {
      println "Existing entity types, skipping creation."
    }
  }

  void createResourceTypesIfRequired() {
    if (ResourceType.count() == 0) {
      println "Fresh Database. Creating resource types."

      ResourceType resourceType

      resourceType = new ResourceType(name: "Energy", ningUrl: "/page/energy-1");
      resourceType.save()
      resourceType = new ResourceType(name: "Water", ningUrl: "/page/water-1");
      resourceType.save()
      resourceType = new ResourceType(name: "Materials", ningUrl: "/page/materials-1");
      resourceType.save()
      resourceType = new ResourceType(name: "Sustainability", ningUrl: "/page/sustainability-1");
      resourceType.save()
      resourceType = new ResourceType(name: "Other");
      resourceType.save()
    }
    else {
      println "Existing resource types, skipping creation."
    }
  }

  void createResourcesIfRequired() {
    if (Resource.count() == 0) {
      println "Fresh Database. Creating resources."

      UnitOfMeasure uomKiloGallon = UnitOfMeasure.findByName("kgal")
      UnitOfMeasure uomKiloWattHour = UnitOfMeasure.findByName("kWh")
      UnitOfMeasure uomTherm =  UnitOfMeasure.findByName("therm")
      UnitOfMeasure uomMetricTon =  UnitOfMeasure.findByName("metric ton")

      ResourceType rtEnergy = ResourceType.get(1)
      ResourceType rtWater = ResourceType.get(2)
      ResourceType rtMaterial = ResourceType.get(3)
      ResourceType rtSustainability = ResourceType.get(4)
      ResourceType rtOther = ResourceType.get(5)
      Resource resource

      resource = new Resource(name: "Electricity", type: rtEnergy, uom: uomKiloWattHour);
      resource.save()
      resource = new Resource(name: "Natural Gas", type: rtEnergy, uom: uomTherm);
      resource.save()
      resource = new Resource(name: "Gasoline", type: rtEnergy, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Diesel", type: rtEnergy, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Freshwater", type: rtWater, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Greywater", type: rtWater, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Blackwater", type: rtWater, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Recycled Water", type: rtWater, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Solid Waste", type: rtSustainability, uom: uomMetricTon);
      resource.save()
      resource = new Resource(name: "CO2e", type: rtSustainability, uom: uomMetricTon);
      resource.save()
      resource = new Resource(name: "Other", type: rtOther, uom: uomMetricTon);
      resource.save()
    }
    else {
      println "Existing resources, skipping creation."
    }
  }

  void createAccountTypesIfRequired() {
    if (AccountType.count() == 0) {
      println "Fresh Database. Creating account types."

      AccountType at

      at = new AccountType(name: "Group")
      at.save()
      at = new AccountType(name: "Building")
      at.save()
      at = new AccountType(name: "Component")
      at.save()
      at = new AccountType(name: "Administrative")
      at.save()
      at = new AccountType(name: "Manufacturing")
      at.save()
      at = new AccountType(name: "Data Center")
      at.save()
      at = new AccountType(name: "Recycling Center")
      at.save()
      at = new AccountType(name: "Recovery Device")
      at.save()
      at = new AccountType(name: "Customer")
      at.save()
    }
    else {
      println "Existing account type values, skipping creation."
    }
  }

  void createTransactionCategoriesIfRequired() {
    if (TransactionCategory.count() == 0) {
      println "Fresh Database. Creating transaction categories."

      ResourceType rtEnergy = ResourceType.get(1)
      ResourceType rtWater = ResourceType.get(2)
      ResourceType rtMaterials = ResourceType.get(3)
      ResourceType rtSustainablity = ResourceType.get(4)
      ResourceType rtOther = ResourceType.get(5)


      Resource resElectricity = Resource.findByName("Electricity")
      Resource resNaturalGas = Resource.findByName("Natural Gas")
      Resource resGasoline = Resource.findByName("Gasoline")
      Resource resDiesel = Resource.findByName("Diesel")
      Resource resFreshwater = Resource.findByName("Freshwater")
      Resource resGraywater = Resource.findByName("Greywater")
      Resource resBlackwater = Resource.findByName("Blackwater")
      Resource resRecycledWater = Resource.findByName("Recycled Water")
      Resource resSolidwaste = Resource.findByName("Solid Waste")
      Resource resCO2e = Resource.findByName("CO2e")
      Resource resOther = Resource.findByName("Other")

      TransactionCategory transactionCategory
      TransactionType transactionType

      transactionCategory = new TransactionCategory(id: TransactionCategory.AC_Scope1, name: "Scope 1")
      transactionCategory.addToTransactionTypes(new TransactionType(transactionCategory: transactionCategory, name: "Direct Cons", resourceType: rtWater))
      transactionCategory.addToTransactionTypes(new TransactionType(transactionCategory: transactionCategory, name: "Direct Cons", resourceType: rtEnergy))
      transactionCategory.addToTransactionTypes(new TransactionType(transactionCategory: transactionCategory, name: "Direct Prod", resourceType: rtWater))
      transactionCategory.addToTransactionTypes(new TransactionType(transactionCategory: transactionCategory, name: "Direct Prod", resourceType: rtEnergy))
      transactionCategory.save()

      transactionCategory = new TransactionCategory(id: TransactionCategory.AC_Scope2, name: "Scope 2")
      transactionCategory.addToTransactionTypes(new TransactionType(transactionCategory: transactionCategory, name: "Indirect Electric", resourceType: rtEnergy))
      transactionCategory.save()

      transactionCategory = new TransactionCategory(id: TransactionCategory.AC_Scope3, name: "Scope 3")
      transactionCategory.addToTransactionTypes(new TransactionType(transactionCategory: transactionCategory, name: "Indirect Cons", resourceType: rtEnergy))
      transactionCategory.addToTransactionTypes(new TransactionType(transactionCategory: transactionCategory, name: "Indirect Cons", resourceType: rtOther))
      transactionCategory.addToTransactionTypes(new TransactionType(transactionCategory: transactionCategory, name: "Indirect Prod", resourceType: rtOther))
      transactionCategory.save()
    } else {
      println "Existing transaction categories, skipping creation."
    }
  }

  void createProjectTypesIfRequired() {
    if (ProjectType.count() == 0) {
      println "Fresh Database. Creating project types."

      ResourceType rtEnergy = ResourceType.get(1)
      ResourceType rtWater = ResourceType.get(2)
      ResourceType rtMaterials = ResourceType.get(3)
      ResourceType rtSustainability = ResourceType.get(4)
      ResourceType rtOther = ResourceType.get(5)
      ProjectType projectType

      projectType = new ProjectType(name: "Recycled Water", resourceType: rtWater,
          ningUrl: "/page/recycled-water", effectFrac: 0.5, description: "")
      projectType.save()

      projectType = new ProjectType(name: "Decentralized Water", resourceType: rtWater,
          ningUrl: "/page/decentralized-water", effectFrac: 0.5, description: "")
      projectType.save()

      projectType = new ProjectType(name: "Water Conservation", resourceType: rtWater,
          ningUrl: "/page/water-conservation", effectFrac: 0.5, description: "")
      projectType.save()

      projectType = new ProjectType(name: "Water Policy", resourceType: rtWater,
          ningUrl: "/page/water-policy-planning", effectFrac: 0.5, description: "")
      projectType.save()

      projectType = new ProjectType(name: "Materials Disposal", resourceType: rtMaterials,
          ningUrl: "/page/materials-disposal", effectFrac: 0.5, description: "")
      projectType.save()

      projectType = new ProjectType(name: "Materials Renew & Recycle", resourceType: rtMaterials,
          ningUrl: "/page/materials-renew-recycle", effectFrac: 0.5, description: "")
      projectType.save()

      projectType = new ProjectType(name: "Smart Grid", resourceType: rtEnergy,
          ningUrl: "/page/energy-smart-grid", effectFrac: 0.5, description: "")
      projectType.save()

      projectType = new ProjectType(name: "Energy Efficiency", resourceType: rtEnergy,
          ningUrl: "/page/energy-efficiency", effectFrac: 0.33, description: "")
      projectType.save()

      projectType = new ProjectType(name: "Energy Conservation", resourceType: rtEnergy,
          ningUrl: "/page/energy-conservation", effectFrac: 0.5, description: "")
      projectType.save()

      projectType = new ProjectType(name: "Energy Renewables", resourceType: rtEnergy,
          ningUrl: "/page/energy-renewables", effectFrac: 0.5, description: "")
      projectType.save()
    }
    else {
      println "Existing project types, skipping creation."
    }
  }

  void createRolesIfRequired() {
    if (Role.count() == 0) {
      println "Fresh Database. Creating roles."

      Role role

      role = new Role(name: "Administrator");
      role.save();

      role = new Role(name: "Member")
      role.save();
    }
    else {
      println "Existing role values, skipping creation."
    }
  }

  void createBasicUsersIfRequired() {
    if (User.count() == 0) {
      println "Fresh Database. Creating basic users."

      Role roleAdministrator = Role.get(1)
      Role roleMember = Role.get(2)
      Profile profile
      User user
      UserRole userRole

      profile = new Profile(fullName: "Jeffrey Risberg", email: "admin@yourhost.com", country: "USA", timeZone: "PST")
      user = new User(userId: "07acujqihoo00", profile: profile, password: "adminpassword")
      user.save()

      userRole = new UserRole(user: user, role: roleAdministrator, effectiveStart: new Date())
      userRole.save();
      user.addToUserRoles(userRole);
      user.save();

      profile = new Profile(fullName: "Spoorthy Ananthai...", email: "admin@yourhost.com", country: "USA", timeZone: "PST")
      user = new User(userId: "SpoorthyAnanthaiah", profile: profile, password: "adminpassword")
      user.save()

      userRole = new UserRole(user: user, role: roleAdministrator, effectiveStart: new Date())
      userRole.save();
      user.addToUserRoles(userRole);
      user.save();

      profile = new Profile(fullName: "Bob Jones", email: "bob@aol.com", country: "USA", timeZone: "PST")
      user = new User(userId: "1111", profile: profile, password: "bobpassword")
      user.save()

      userRole = new UserRole(user: user, role: roleMember, effectiveStart: new Date())
      userRole.save();

      profile = new Profile(fullName: "Sally Smith", email: "sally@aol.com", country: "USA", timeZone: "PST")
      user = new User(userId: "2222", profile: profile, password: "sallypassword")
      user.save()

      userRole = new UserRole(user: user, role: roleMember, effectiveStart: new Date())
      userRole.save();

      profile = new Profile(fullName: "Tom Woods", email: "tom@aol.com", country: "USA", timeZone: "PST")
      user = new User(userId: "3333", profile: profile, password: "tompassword")
      user.save()

      userRole = new UserRole(user: user, role: roleMember, effectiveStart: new Date())
      userRole.save();
    } else {
      println "Existing users, skipping creation."
    }

    if (Preferences.count() == 0) {
      println "Fresh Database. Creating preferences."

      GeoScale building = GeoScale.findByScale(1);
      GeoScale cluster = GeoScale.findByScale(2);
      GeoScale catchment = GeoScale.findByScale(3);
      TimeScale tsYear = TimeScale.findByScale(7);

      Preferences preferences

      preferences = new Preferences(geoScale: building, timeScale: tsYear)
      preferences.save()

      preferences = new Preferences(geoScale: cluster, timeScale: tsYear)
      preferences.save()

      preferences = new Preferences(geoScale: catchment, timeScale: tsYear)
      preferences.save()
    } else {
      println "Existing preferences, skipping creation"
    }
  }

  void createPipelineFootsIfRequired() {
    if (PipelineFoot.count() == 0) {
      println "Fresh Database. Creating pipeline foots."

      double basic10Price = 10 * 2.84023668639053
      double basic15Price = basic10Price * 1.25
      double basic20Price = basic15Price * 1.25
      double basic25Price = basic20Price * 1.2
      double basic30Price = basic25Price * 1.3
      double basic40Price = basic30Price * 1.3

      PipelineFoot pipelineFoot

      pipelineFoot = new PipelineFoot(seqNum:  1, name: '1.0" Above Ground', cost: basic10Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum:  2, name: '1.0" In Building', cost: basic10Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum:  3, name: '1.0" Underground, Paved Finish', cost: basic10Price)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum:  4, name: '1.0" Underground, Unpaved Finish', cost: basic10Price/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum:  5, name: '1.5" Above Ground', cost: basic15Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum:  6, name: '1.5" In Building', cost: basic15Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum:  7, name: '1.5" Underground, Paved Finish', cost: basic15Price)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum:  8, name: '1.5" Underground, Unpaved Finish', cost: basic15Price/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum:  9, name: '2.0" Above Ground', cost: basic20Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 10, name: '2.0" In Building', cost: basic20Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 11, name: '2.0" Underground, Paved Finish', cost: basic20Price)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 12, name: '2.0" Underground, Unpaved Finish', cost: basic20Price/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 13, name: '2.5" Above Ground', cost: basic25Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 14, name: '2.5" In Building', cost: basic25Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 15, name: '2.5" Underground, Paved Finish', cost: basic25Price)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 16, name: '2.5" Underground, Unpaved Finish', cost: basic25Price/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 17, name: '3.0" Above Ground', cost: basic30Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 18, name: '3.0" In Building', cost: basic30Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 19, name: '3.0" Underground, Paved Finish', cost: basic30Price)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 20, name: '3.0" Underground, Unpaved Finish', cost: basic30Price/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 21, name: '4.0" Above Ground', cost: basic40Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 22, name: '4.0" In Building', cost: basic40Price/1.5/1.5)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 23, name: '4.0" Underground, Paved Finish', cost: basic40Price)
      pipelineFoot.save()
      pipelineFoot = new PipelineFoot(seqNum: 24, name: '4.0" Underground, Unpaved Finish', cost: basic40Price/1.5)
      pipelineFoot.save()
    }
    else {
      println "Existing pipeline foots, skipping creation."
    }
  }

  void createProductsIfRequired() {
    if (ProductCategory.count() == 0) {
      println "Fresh Database. Creating product categories."

      ProductCategory productCategory

      productCategory = new ProductCategory(name: "Energy")
      productCategory.save()
      productCategory = new ProductCategory(name: "Water")
      productCategory.save()
    }
    else {
      println "Existing product categories, skipping creation."
    }

    if (ProductAttribute.count() == 0) {
      println "Fresh Database. Creating product attributes."

      ProductAttribute productAttribute

      productAttribute = new ProductAttribute(name: "attr1", type: "")
      productAttribute.save()
      productAttribute = new ProductAttribute(name: "attr2", type: "")
      productAttribute.save()
    }
    else {
      println "Existing product attributes, skipping creation."
    }

    if (Product.count() == 0) {
      println "Fresh Database. Creating products."

      ProductCategory energyProductCategory = ProductCategory.findByName("Energy")
      ProductCategory waterProductCategory = ProductCategory.findByName("Water")
      Product product

      product = new Product
          (name: "Vantage Data Centers", description: "", productCategory: energyProductCategory)
      product.save()
      product = new Product(name: "Nalco", description: "", productCategory: energyProductCategory)
      product.save()
      product = new Product(name: "GE Chemicals", description: "", productCategory: energyProductCategory)
      product.save()
      product = new Product(name: "Illuminati Lighting", description: "", productCategory: energyProductCategory)
      product.save()
    }
    else {
      println "Existing products, skipping creation."
    }

    if (ProductAttributeValue.count() == 0) {
      println "Fresh Database. Creating product attribute values."

      ProductAttribute productAttribute1 = ProductAttribute.findByName("attr1")
      ProductAttribute productAttribute2 = ProductAttribute.findByName("attr2")
      Product productVantage = Product.findByName("Vantage Data Centers")
      ProductAttributeValue productAttributeValue

      productAttributeValue = new ProductAttributeValue(product: productVantage, productAttribute: productAttribute1, value: 100)
      productAttributeValue.save()
    }
    else {
      println "Existing product attribute values, skipping creation."
    }
  }

  void createContentQualifiersIfRequired() {
    if (ContentQualifier.count() == 0) {
      println "Fresh Database. Creating contentQualifiers."

      ContentQualifier contentQualifier

      contentQualifier = new ContentQualifier(name: "Water", type: "r", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Air", type: "r", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Energy", type: "r", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Materials", type: "r", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()

      contentQualifier = new ContentQualifier(name: "Ecosystem Research", type: "s", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Cleantech Innovation", type: "s", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Infrastructure & Facilities", type: "s", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Enterprise Sustainability", type: "s", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Public Policy & Regulation", type: "s", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()

      contentQualifier = new ContentQualifier(name: "Solar Energy", type: "m", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Wind Energy", type: "m", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Smart Grid", type: "m", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Energy Storage", type: "m", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Electric Vehicles", type: "m", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
      contentQualifier = new ContentQualifier(name: "Green Building", type: "m", imgUrl: '/images/boxShadow.png')
      contentQualifier.save()
    }
    else {
      println "Existing contentQualifiers, skipping creation."
    }
  }

  void createContentItemsIfRequired() {
    if (ContentItem.count() == 0) {
      println "Fresh Database. Creating content items."

      ContentItem contentItem
      ContentSection contentSection
      Address address
      Contact contact
      Comment comment

      contentItem = new ContentItem
          (name: "Default", contentItemType: ContentItemType.ORGANIZATION,
          partnerFlag: true, approvedFlag: true, paidFlag: true,
          url: "http://ecocloud1.ning.com/page/vantage-data-centers")
      contentItem.save()

      // Vendors
      address = new Address(addressLine1: "2675 Walsh Avenue", city: "Santa Clara",
          stateCode: Address.STATES.getAt(6), postalCode: "")
      address.save()
      //contact = new Contact(contactName: "David Herman", phoneNo: "800-856-6128", email: "dherman@nalco.com")
      //contact.save()
      contentSection = new ContentSection(address: address)
      contentItem = new ContentItem
          (name: "Vantage Data Centers", contentItemType: ContentItemType.ORGANIZATION,
          address: address,
          partnerFlag: true, approvedFlag: true, paidFlag: true,
          url: "http://ecocloud1.ning.com/page/vantage-data-centers")
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "electricity")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy")

      address = new Address(addressLine1: "1503 Grant Road, Suite 202", city: "Mountain View",
          stateCode: Address.STATES.getAt(6), postalCode: "")
      address.save()
      //contact = new Contact(contactName: "David Herman", phoneNo: "800-856-6128", email: "dherman@nalco.com")
      //contact.save()
      contentSection = new ContentSection(address: address)
      contentItem = new ContentItem
          (name: "Daintree Networks", contentItemType: ContentItemType.ORGANIZATION,
          address: address,
          partnerFlag: true, approvedFlag: true, paidFlag: true,
          url: "http://ecocloud1.ning.com/page/daintree-networks")
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy")

      address = new Address(addressLine1: "", city: "",
          stateCode: Address.STATES.getAt(6), postalCode: "")
      address.save()
      contact = new Contact(contactName: "Jerry C. Mathew", phoneNo: "510-295-3065", email: "jerry.mathew@ge.com")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.gewater.com")
      contentItem = new ContentItem(name: "GE Infrastructure Water and Process Technologies",
          description: "Provider of industrial chemicals",
          contentItemType: ContentItemType.ORGANIZATION,
          partnerFlag: true, approvedFlag: true, paidFlag: true,
          url: "http://ecocloud1.ning.com/page/ge-infrastructure-water-and",
          website: "www.gewater.com", address: address, contact: contact, paid: true)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "recycled-water")

      contentItem = new ContentItem(name: "Nalco Company",
          description: "Cooling Tower service provider, chemical, and controller contentItem",
          contentItemType: ContentItemType.ORGANIZATION,
          partnerFlag: true, approvedFlag: true, paidFlag: true,
          url: "http://ecocloud1.ning.com/page/nalco-company", paid: true)
      address = new Address(addressLine1: "1633 Bayshore Highway", addressLine2: "Suite 249", city: "Burlingame",
          stateCode: Address.STATES.getAt(6), postalCode: "94010")
      address.save()
      contact = new Contact(contactName: "David Herman", phoneNo: "800-856-6128", email: "dherman@nalco.com")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.nalco.com")
      contentItem.addToContentSections(contentSection)
      address = new Address(addressLine1: "1633 Bayshore Highway", addressLine2: "Suite 249", city: "Burlingame",
          stateCode: Address.STATES.getAt(6), postalCode: "94010")
      address.save()
      contact = new Contact(contactName: "David Herman", phoneNo: "800-856-6128", email: "dherman@nalco.com")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.nalco.com")
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "recycled-water")

      address = new Address(addressLine1: "1100 El Camino Real", addressLine2: "Suite 911", city: "Millbrae",
          stateCode: Address.STATES.getAt(6), postalCode: "94030")
      address.save()
      contact = new Contact(contactName: "Virginia Gibson", phoneNo: "650-921-3377", email: "vasfca@aol.com")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact)
      contentItem = new ContentItem(approvedFlag: true, name: "Reliant Water Management",
          description: "Provider of wastewater treatment materials",
          contentItemType: ContentItemType.ORGANIZATION, paid: false)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "recycled-water")

      address = new Address(addressLine1: "384 Bel Marin Keys Blvd.", addressLine2: "Suite 145", city: "Novato",
          stateCode: Address.STATES.getAt(6), postalCode: "94949")
      address.save()
      contact = new Contact(contactName: "(sales)", phoneNo: "415-945-9383", email: "info@etwater.com")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.etwater.com")
      contentItem = new ContentItem(approvedFlag: true, name: "ET Water",
          description: "Water conservation contentItem",
          contentItemType: ContentItemType.ORGANIZATION,
          website: "www.etwater.com", address: address, contact: contact, paid: false)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-conservation")

      address = new Address(addressLine1: "207 King Street", addressLine2: "Suite 300", city: "San Francisco",
          stateCode: Address.STATES.getAt(6), postalCode: "94010")
      address.save()
      contact = new Contact(contactName: "Phil Williams", phoneNo: "415-978-1000", email: "phil.williams@webcor.com")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "webcor.com")
      contentItem = new ContentItem(approvedFlag: true, name: "WebCor Builders",
          description: "Construction firm",
          contentItemType: ContentItemType.ORGANIZATION,
          partnerFlag: true,
          website: "webcor.com", address: address, contact: contact, paid: false)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "construction")

      address = new Address(addressLine1: "1717 Solano Way", addressLine2: "Unit 34", city: "Concord",
          stateCode: Address.STATES.getAt(6), postalCode: "94520")
      address.save()
      contact = new Contact(contactName: "Bryan Smith", phoneNo: "925-671-2888", email: "bryan@telstar.org")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.telstarinc.com")
      contentItem = new ContentItem(approvedFlag: true, name:"Telstar Inc.",
          description: "Maintenance of all Water Treatment Plant and Pump Station Instruments",
          contentItemType: ContentItemType.ORGANIZATION,
          partnerFlag: true,
          website: "www.telstarinc.com", address: address, contact: contact, paid: true)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "recycled-water")

      address = new Address(addressLine1: "510 Logue Ave", city: "Mountain View",
          stateCode: Address.STATES.getAt(6), postalCode: "94043")
      address.save()
      contact = new Contact(contactName: "(sales)", phoneNo: "650-623-7302")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.solfocus.com")
      contentItem = new ContentItem(approvedFlag: true, name: "Solfocus",
          description: "Solar array provider",
          contentItemType: ContentItemType.ORGANIZATION,
          partnerFlag: true, paidFlag: true)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-renewables")

      address = new Address(addressLine1: "6160 Stoneridge Mall Rd.", addressLine2: "Ste. 200", city: "Pleasanton",
          stateCode: Address.STATES.getAt(6), postalCode: "94588")
      address.save()
      contact = new Contact(contactName: "(sales)", phoneNo: "925-463-7100")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.schneider-electric.com")
      contentItem = new ContentItem(approvedFlag: true, name: "Schneider Electric",
          description: "Electric power management provider",
          contentItemType: ContentItemType.ORGANIZATION,
          website: "www.schneider-electric.com", address: address, contact: contact, paid: false)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-efficiency")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-conservation")

      address = new Address(addressLine1: "333 Vintage Park Drive", addressLine2: "Suite 144", city: "Foster City",
          stateCode: Address.STATES.getAt(6), postalCode: "94404")
      address.save()
      contact = new Contact(contactName: "(sales)", phoneNo: "888-765-2489")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.solarcity.com")
      contentItem = new ContentItem(approvedFlag: true, name: "Solar City",
          description: "Solar array provider",
          contentItemType: ContentItemType.ORGANIZATION, paid: true)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-renewables")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-efficiency")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-conservation")

      // Partners
      address = new Address(addressLine1: "1416 9th Street", city: "Sacramento",
          stateCode: Address.STATES.getAt(6), postalCode: "95814")
      address.save()
      contact = new Contact(contactName: "Public Affairs Office", phoneNo: "916-653-6192")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.water.ca.gov")
      contentItem = new ContentItem(approvedFlag: true, name: "Department of Water Resources",
          description: "State-level water agency",
          contentItemType: ContentItemType.ORGANIZATION,
          website: "www.water.ca.gov", address: address, contact: contact, paid: true)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-conservation")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-policy-planning")

      address = new Address(addressLine1: "5750 Almaden Expressway", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95118")
      address.save()
      contact = new Contact(contactName: "Pam John", phoneNo: "408-265-2607")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.valleywater.org")
      contentItem = new ContentItem(approvedFlag: true, name: "Santa Clara Valley Water District",
          description: "Primary water agency",
          partnerFlag: true,
          contentItemType: ContentItemType.ORGANIZATION, paid: true)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-conservation")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-policy-planning")
      tagUsageService.addTag("ContentItem", contentItem.id, "recycled-water")

      address = new Address(addressLine1: "1515 Clay Street", addressLine2: "Suite 1400", city: "Oakland",
          stateCode: Address.STATES.getAt(6), postalCode: "94612")
      address.save()
      contact = new Contact(contactName: "Ben Livsey", phoneNo: "510-622-2230", email: "blivsey@waterboards.ca.gov")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.waterboards.ca.gov")
      contentItem = new ContentItem(approvedFlag: true, name: "San Francisco Bay Water Quality Control Board",
          description: "Agency",
          contentItemType: ContentItemType.ORGANIZATION, paid: false)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-conservation")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-policy-planning")

      address = new Address(addressLine1: "3025 Tuers Road", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95121")
      address.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.sanjoseca.gov/sbwr")
      contentItem = new ContentItem(approvedFlag: true, name: "South Bay Water Recycling",
          description: "Provides recycled water and operates purple pipe network",
          contentItemType: ContentItemType.ORGANIZATION,
          partnerFlag: true, paid: true)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "recycled-water")

      address = new Address(addressLine1: "110 W. Taylor Street", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95110")
      address.save()
      contentSection = new ContentSection(address: address)
      contentItem = new ContentItem(approvedFlag: true, name: "South Jose Water Company",
          description: "Municipal water supply company",
          contentItemType: ContentItemType.ORGANIZATION, paid: false)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")

      address = new Address(addressLine1: "20 Great Oaks Blvd", addressLine2: "Suite 120", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95119")
      address.save()
      contact = new Contact(contactName: "Bobby Dartez", phoneNo: "408-227-9540")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.greatoakswater.com")
      contentItem = new ContentItem(approvedFlag: true, name: "Great Oaks Water Company",
          description: "Municipal water supply company",
          contentItemType: ContentItemType.ORGANIZATION, paid: false)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")

      address = new Address(addressLine1: "1500 Warburton Avenue", city: "Santa Clara",
          stateCode: Address.STATES.getAt(6), postalCode: "95050")
      address.save()
      contact = new Contact(contactName: "Kevin Riley", phoneNo: "408-615-2450")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "santaclaraca.gov")
      contentItem = new ContentItem(approvedFlag: true, name: "City of Santa Clara",
          description: "Municipality",
          partnerFlag: true,
          contentItemType: ContentItemType.ORGANIZATION, paid: false)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-conservation")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-policy-planning")

      address = new Address(addressLine1: "1444 Borregas Avenue", city: "Sunnyvale",
          stateCode: Address.STATES.getAt(6), postalCode: "94089")
      address.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "sunnyvaleca.gov")
      contentItem = new ContentItem(approvedFlag: true, name: "City of Sunnyvale",
          description: "Municipality",
          partnerFlag: true,
          contentItemType: ContentItemType.ORGANIZATION, paid: false)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-conservation")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-renewables")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-conservation")

      address = new Address(addressLine1: "250 Hamilton Ave", city: "Palo Alto",
          stateCode: Address.STATES.getAt(6), postalCode: "94301")
      address.save()
      contact = new Contact(contactName: "Jamie Allen", phoneNo: "650-329-2243")
      contact.save()
      contentSection = new ContentSection(address: address, contact: contact, website: "www.cityofpaloalto.org")
      contentItem = new ContentItem(approvedFlag: true, name: "City of Palo Alto",
          description: "Municipality",
          partnerFlag: true,
          contentItemType: ContentItemType.ORGANIZATION, paid: true)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-conservation")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-policy-planning")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-renewables")
      tagUsageService.addTag("ContentItem", contentItem.id, "energy-conservation")

      address = new Address(addressLine1: "155 Bovet Road", addressLine2: "Suite 302", city: "San Mateo",
          stateCode: Address.STATES.getAt(6), postalCode: "94402-3111")
      address.save()
      contentSection = new ContentSection(address: address)
      contentItem = new ContentItem(approvedFlag: true, name: "Bay Area Water Supply and Conservation Agency",
          description: "Agency",
          contentItemType: ContentItemType.ORGANIZATION)
      contentItem.addToContentSections(contentSection)
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-conservation")
      tagUsageService.addTag("ContentItem", contentItem.id, "water-policy-planning")
      tagUsageService.addTag("ContentItem", contentItem.id, "recycled-water")

      // Documents
      //author: "Elaina Marshalek"
      contentItem = new ContentItem
          (name: "SJ State Recycled Water Case Study",
          contentItemType: ContentItemType.BOOK,
          partnerFlag: true, approvedFlag: true, paidFlag: true,
          url: "http://ecocloud1.ning.com/page/recycled-water-san-jose-state")
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "energy")

      //author: "Jamie Workman"
      contentItem = new ContentItem
          (name: "Water Ownership and Aquajust",
          contentItemType: ContentItemType.BOOK,
          partnerFlag: true, approvedFlag: true, paidFlag: true,
          url: "http://ecocloud1.ning.com/page/water-ownership-and-aquajust")
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")

      //author: "Elaina Marshalek"
      contentItem = new ContentItem
          (name: "Air Products Case Study",
          contentItemType: ContentItemType.BOOK,
          partnerFlag: true, approvedFlag: true,
          url: "http://ecocloud1.ning.com/page/recycled-water-air-products")
      contentItem.save()
      tagUsageService.addTag("ContentItem", contentItem.id, "water")
      tagUsageService.addTag("ContentItem", contentItem.id, "recycled-water")
    }
    else {
      println "Existing content items, skipping creation."
    }
  }

  void createProvidersIfRequired() {
    if (Provider.count() == 0) {
      println "Fresh Database. Creating providers."

      EntityType etProvider = EntityType.findByName("Provider")
      Address address
      Contact contact
      Provider provider
      Comment comment

      // San Jose Muni Water
      address = new Address(addressLine1: "3025 Tuers Road", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95121-2430")
      address.save()
      contact = new Contact(contactName: "Customer Contact Center", phoneNo: "408-535-3500")
      contact.save()
      provider = new Provider(name: "San Jose Municipal Water", isPartner: true, isVendor: false,
          description: "Public utility",
          website: "www.sjmuniwater.com", address: address, contact: contact, paid: true)
      provider.save()
      tagUsageService.addTag("Provider", provider.id, "water")
      tagUsageService.addTag("Provider", provider.id, "recycled-water")

      // Santa Clara
      address = new Address(addressLine1: "1500 Warburton Avenue", city: "Santa Clara",
          stateCode: Address.STATES.getAt(6), postalCode: "95050")
      address.save()
      contact = new Contact(contactName: "Customer Contact Center", phoneNo: "408-615-2300")
      contact.save()
      provider = new Provider(name: "City of Santa Clara - Water Service", isPartner: true, isVendor: false,
          description: "Public utility",
          website: "www.santaclaraca.gov", address: address, contact: contact, paid: true)
      provider.save()
      tagUsageService.addTag("Provider", provider.id, "water")
      tagUsageService.addTag("Provider", provider.id, "recycled-water")

      // Milpitas
      address = new Address(addressLine1: "455 East Calaveras Boulevard", city: "Milpitas",
          stateCode: Address.STATES.getAt(6), postalCode: "95035")
      address.save()
      contact = new Contact(contactName: "Customer Contact Center", phoneNo: "408-586-3000")
      contact.save()
      provider = new Provider(name: "City of Milpitas - Water Service", isPartner: true, isVendor: false,
          description: "Public utility",
          website: "www.ci.milpitas.ca.gov", address: address, contact: contact, paid: true)
      provider.save()
      tagUsageService.addTag("Provider", provider.id, "water")
      tagUsageService.addTag("Provider", provider.id, "recycled-water")

      // San Jose Water Company
      address = new Address(addressLine1: "110 W. Taylor St.", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95110")
      address.save()
      contact = new Contact(contactName: "Customer Contact Center", phoneNo: "408-279-7900")
      contact.save()
      provider = new Provider(name: "San Jose Water Company", isPartner: true, isVendor: false,
          description: "Investor-owned utility",
          website: "www.sjwater.gov", address: address, contact: contact, paid: false)
      provider.save()
      tagUsageService.addTag("Provider", provider.id, "water")
      tagUsageService.addTag("Provider", provider.id, "recycled-water")
    }
    else {
      println "Existing providers, skipping creation"
    }
  }

  void createResourcePricesIfRequired() {
    if (ResourcePrice.count() == 0) {
      println "Fresh Database. Creating resource prices."

      Resource resElectricity = Resource.findByName("Electricity")
      Resource resNaturalGas = Resource.findByName("Natural Gas")
      Resource resGasoline = Resource.findByName("Gasoline")
      Resource resDiesel = Resource.findByName("Diesel")
      Resource resFreshwater = Resource.findByName("Freshwater")
      Resource resGraywater = Resource.findByName("Greywater")
      Resource resBlackwater = Resource.findByName("Blackwater")
      Resource resRecycledWater = Resource.findByName("Recycled Water")
      Resource resSolidwaste = Resource.findByName("Solid Waste")
      Resource resCO2e = Resource.findByName("CO2e")
      Resource resOther = Resource.findByName("Other")

      Date date2008 = new Date(108, 1, 1);
      Date date2009 = new Date(109, 1, 1);
      Date date2010 = new Date(110, 1, 1);
      Provider provider
      ResourcePrice resourcePrice

      // offerings by San Jose Municipal Water
      provider = Provider.findByName("San Jose Municipal Water")
      resourceService.saveResourcePrice(provider, resFreshwater, date2008, 2.44)
      resourceService.saveResourcePrice(provider, resRecycledWater, date2008, 1.01)

      // offerings by City of Santa Clara
      provider = Provider.findByName("City of Santa Clara - Water Service")
      resourceService.saveResourcePrice(provider, resFreshwater, date2009, 2.74)
      resourceService.saveResourcePrice(provider, resRecycledWater, date2010, 1.29)

      // offerings by City of Milpitas
      provider = Provider.findByName("City of Milpitas - Water Service")
      resourceService.saveResourcePrice(provider, resFreshwater, date2009, 4.04)
      resourceService.saveResourcePrice(provider, resRecycledWater, date2010, 2.02)


      // offerings by San Jose Water Company
      provider = Provider.findByName("San Jose Water Company")
      resourceService.saveResourcePrice(provider, resFreshwater, date2009, 2.52)
      resourceService.saveResourcePrice(provider, resRecycledWater, date2010, 1.55)
    }
    else {
      println "Existing resource prices, skipping creation."
    }
  }

  void createTestProjectsIfRequired() {
    if (Project.count() == 0) {
      println "Fresh Database. Creating two accounts/projects."

      // First set up the accounts
      AccountType atBuilding = AccountType.get(2)
      GeoScale gsCluster = GeoScale.get(2)
      Address address
      Comment comment
      Account account
      if(false){
        address = new Address(
            addressLine1: "1515 Felipe Avenue", city: "San Jose",
            stateCode: Address.STATES.getAt(6), postalCode: "95124",
            latitude: 37.336355, longitude: -121.852938)
        address.save();

        Account account1 = new Account(type: atBuilding, name: "NextGen Gases",
            geoScale: gsCluster, address: address,
            description: "NextGen is a leading producer of industrial gases")
        account1.save()
        tagUsageService.addTag("Account", account1.id, "factory")
        tagUsageService.addTag("Account", account1.id, "industrial-gases")

        address = new Address(
            addressLine1: "123 Homestead Road", city: "Santa Clara",
            stateCode: Address.STATES.getAt(6), postalCode: "95050",
            latitude: 37.346355, longitude: -121.952938)
        address.save();

        Account account2 = new Account(type: atBuilding, name: "Silicon Valley PowerGen Inc.",
            geoScale: gsCluster, address: address,
            description: "SVPG is a local power generation company")
        account2.save()
        tagUsageService.addTag("Account", account2.id, "energy")
        tagUsageService.addTag("Account", account2.id, "electricity")

        // Now add the projects
        ProjectStatus psPending = ProjectStatus.findBySequence(1)
        ProjectType ptRecycledWater = ProjectType.findByName("Recycled Water")
        Provider provSanJoseMuni = Provider.get(1)
        Provider provSantaClara = Provider.get(2)
        PipelineFoot pipelineFoot20Paved = PipelineFoot.findBySeqNum(11)
        PipelineFoot pipelineFoot20Unpaved = PipelineFoot.findBySeqNum(12)
        Project project
        ROIProject roiProject

        project = new Project(type: ptRecycledWater, account: account1, status: psPending,
            name: "Cooling Tower Retrofit",
            startDate: new Date(110,1,1),
            budget: 25000, priority: 2,
            description: "Changeover on tower T-85")
        project.save();
        tagUsageService.addTag("Project", project.id, "water")
        tagUsageService.addTag("Project", project.id, "recycled-water")

        roiProject = new ROIProject(project: project,
            currentMonthlyWaterUse: 3740000,
            currentProvider: provSanJoseMuni, proposedProvider: provSanJoseMuni,
            currentCycles: 3.5, proposedCycles: 4,
            distance1: 10, pipelineFoot1: pipelineFoot20Paved,
            otherCapitalImprovements: 10000)
        roiProject.save();
        projectService.recalc(project, roiProject, null)
        project.save()

        project = new Project(type: ptRecycledWater, account: account2, status: psPending,
            name: "Generator Modification",
            startDate: new Date(110,4,15),
            budget: 35000, priority: 3,
            description: "Upgrade of generator G-34R")
        project.save();
        tagUsageService.addTag("Project", project.id, "water")
        tagUsageService.addTag("Project", project.id, "energy")
        tagUsageService.addTag("Project", project.id, "recycled-water")

        roiProject = new ROIProject(project: project,
            currentMonthlyWaterUse: 2000000,
            currentProvider: provSantaClara, proposedProvider: provSantaClara,
            currentCycles: 2.5, proposedCycles: 3.75,
            distance1: 10, pipelineFoot1: pipelineFoot20Paved,
            distance2: 10, pipelineFoot2: pipelineFoot20Unpaved,
            otherCapitalImprovements: 15000)
        roiProject.save();

        projectService.recalc(project, roiProject, null)
        project.save()
      }
    }
    else {
      println "Existing projects, skipping creation."
    }
  }

  void createSampleDataIfRequired() {
    if (Account.count() == 0) {
      println "Fresh Database. Creating sample data accounts."

      // Build a few Accounts, put comments on some
      GeoScale gsBuilding = GeoScale.findByName("Building")
      if (gsBuilding == null) throw new RuntimeException("cannot find building")
      GeoScale gsCluster = GeoScale.findByName("Cluster")
      if (gsCluster == null) throw new RuntimeException("cannot find cluster")
      GeoScale gsCatchment = GeoScale.findByName("Catchment")
      if (gsCatchment == null) throw new RuntimeException("cannot find catchment")
      GeoScale gsRegion = GeoScale.findByName("Region")
      if (gsRegion == null) throw new RuntimeException("cannot find region")

      AccountType atGroup = AccountType.findByName("Group")
      if (atGroup == null) throw new RuntimeException("cannot find group")
      AccountType atBuilding = AccountType.findByName("Building")
      if (atBuilding == null) throw new RuntimeException("cannot find b")
      AccountType atComponent = AccountType.findByName("Component")
      if (atComponent == null) throw new RuntimeException("cannot find c")
      AccountType atDataCenter = AccountType.findByName("Data Center")
      if (atDataCenter == null) throw new RuntimeException("cannot find dc")
      AccountType atRecoveryDevice = AccountType.findByName("Recovery Device")
      if (atRecoveryDevice == null) throw new RuntimeException("cannot find rd")

      EntityType etAccount = EntityType.findByName("Account")
      User userJR = User.get(1)
      User userSA = User.get(2)
      User user3 = User.get(3)
      User user4 = User.get(4)

      Date early1910 = new Date(10, 1, 1)
      Date early1920 = new Date(20, 1, 1)
      Date early1930 = new Date(30, 1, 1)
      Date early1998 = new Date(98, 1, 1)
      Date early2000 = new Date(100, 1, 1)
      Date early2008 = new Date(108, 1, 1)
      Date early2009 = new Date(109, 1, 1)

      Resource resFreshwater = Resource.findByName("Freshwater")
      Resource resGraywater = Resource.findByName("Greywater")
      Resource resBlackwater = Resource.findByName("Blackwater")
      Resource resRecycledWater = Resource.findByName("Recycled Water")
      Resource resSolidWaste = Resource.findByName("Solid Waste")

      UnitOfMeasure uomKiloGallon = UnitOfMeasure.findByName("kgal")
      UnitOfMeasure uomKiloWattHour = UnitOfMeasure.findByName("kWh")
      UnitOfMeasure uomTherm =  UnitOfMeasure.findByName("therm")
      UnitOfMeasure uomMetricTon =  UnitOfMeasure.findByName("metric ton")

      Address address
      Account account
      AccountLink accountLink
      Comment comment
      Flow flow

      // root
      address = new Address();
      address.save()
      Account cAccount = accountService.saveAccountAndLink("California", address,
          "State-wide group", gsCluster,atGroup, null,  null)

      //Northern California account
      address = new Address();
      address.save()
      Account ncAccount = accountService.saveAccountAndLink( "Northern California", address,
          "State-wide group",gsCluster,atGroup, cAccount, early1910)

      //Southern California account
      address = new Address();
      address.save()
      Account scAccount = accountService.saveAccountAndLink("Southern California", address,
          "State-wide group", gsCluster,atGroup,cAccount,early1920 )

      //San Jose Campus
      address = new Address(addressLine1: "One Washington Square", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95192-0080",
          latitude: 37.337229, longitude: -121.881745);
      address.save()
      Account sjsuAccount = accountService.saveAccountAndLink("SJSU Campus", address,
          "San Jose State University main campus", gsCluster, atGroup,
          ncAccount, early1920)

      //Eng quad on campus
      address = new Address(addressLine1: "S 7th St & E San Fernando St", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95112-2065",
          latitude: 37.337401, longitude: -121.882025);
      address.save()

      Account engAccount = accountService.saveAccountAndLink("ENG", address,
          "Engineering quad", gsCluster, atGroup, sjsuAccount, early2008)

      comment = new Comment(entityType: etAccount, entityId: engAccount.id,
          user: user3, body: "Eng Quad has several buildings")
      comment.save()
      comment.dateCreated = new Date(110, 3, 14)
      comment.save()

      Account eqDrainageAccount = accountService.saveAccountAndLink("E-Drains", address,
          "Eng Quad drainage system", gsCluster, atComponent, engAccount, early2008);

      //Clark Laboratory Building
      address = new Address(addressLine1: "S 6th St & E San Fernando St", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95112-2065",
          latitude: 37.336247, longitude: -121.882555);

      address.save()

      Account labAccount = accountService.saveAccountAndLink("ENG-CLK", address,
          "Clark Hall with laboratory facilities", gsCluster, atBuilding, engAccount, early1930);

      comment = new Comment(entityType: etAccount, entityId: labAccount.id,
          user: user3, body: "Eng-Clk could use recycle water")
      comment.save()
      comment.dateCreated = new Date(110, 3, 14)
      comment.save()

      //Fountain in the Eng Quad
      address = new Address(addressLine1: "124 North 7th Street", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95112-5431",
          latitude: 37.336625, longitude: -121.881429);
      address.save()
      Account eqFtnAccount =  accountService.saveAccountAndLink("E-FTN", address,
          "Fountains in Engineering Quad", gsCluster, atBuilding, engAccount, early2000);

      //Administration building
      address = new Address(addressLine1: "S 8th St & E San Fernando St", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95112-2065",
          latitude: 37.336762, longitude: -121.882895);
      address.save()
      Account adminAccount = accountService.saveAccountAndLink("ADMIN", address,
          "Administration building", gsCluster, atBuilding, sjsuAccount, early1920);

      comment = new Comment(entityType: etAccount, entityId: adminAccount.id,
          user: user3, body: "Administration building must use potable water for drinking")
      comment.save()
      comment.dateCreated = new Date(110, 3, 14)
      comment.save()

      //Fountain near admin building
      address = new Address(addressLine1: "124 North 7th Street", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95112-5431",
          latitude: 37.336603, longitude: -121.88336);
      address.save()
      Account adminFtnAccount = accountService.saveAccountAndLink("A-FTN", address,
          "Fountains near admin Building", gsCluster, atBuilding,
          adminAccount, early2000);

      comment = new Comment(entityType: etAccount, entityId: adminFtnAccount.id,
          user: user3, body: "Fountains may use recycled water")
      comment.save()
      comment.dateCreated = new Date(110, 3, 14)
      comment.save()

      //Campus Village
      address = new Address(addressLine1: "125 North 6th Street", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95112-5431",
          latitude: 37.333733, longitude: -121.883913);
      address.save()
      Account cvAccount = accountService.saveAccountAndLink("CV", address,
          "Campus Village with dorms and dining commons", gsCluster, atBuilding,
          sjsuAccount, early1920)

      comment = new Comment(entityType: etAccount, entityId: cvAccount.id,
          user: user3, body: "Campus Village has been updated with new copper pipes")
      comment.save()
      comment.dateCreated = new Date(110, 3, 14)
      comment.save()

      //Dining Hall
      address = new Address(addressLine1: "125 North 6th Street", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95112-5431",
          latitude:37.33381, longitude: -121.88358);
      address.save()
      Account dcAccount = accountService.saveAccountAndLink("DC", address,
          "Dining Commons for dining", gsCluster, atBuilding, cvAccount, early1930);

      comment = new Comment(entityType: etAccount, entityId: dcAccount.id,
          user: user3, body: "Dining Commons must use potable water in dining hall")
      comment.save()
      comment.dateCreated = new Date(110, 3, 14)
      comment.save()

      //Drainage near admin building
      address = new Address(addressLine1: "125 North 6th Street", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95112-5431",
          latitude: 37.336578, longitude: -121.883012);
      address.save()
      Account cvDrainageAccount = accountService.saveAccountAndLink("A-Drains", address,
          "Campus-level drainage system", gsCluster, atBuilding, cvAccount, early1930);

      // put the flows for the campus here
      flowService.saveFlow(engAccount, eqFtnAccount, uomKiloGallon, resFreshwater, 40)
      flowService.saveFlow(eqFtnAccount, eqDrainageAccount,uomKiloGallon, resGraywater, 35)
      flowService.saveFlow(adminAccount, adminFtnAccount, uomKiloGallon, resFreshwater, 50)
      flowService.saveFlow(adminFtnAccount, cvDrainageAccount, uomKiloGallon, resGraywater, 40)

      // Building-scale begins here

      // supply line
      Account wslAccount = accountService.saveAccountAndLink("Water Supply Line", address,
          "Primary water supply line", gsBuilding, atComponent, cvAccount, early1930)

      // kitchen
      Account kitAccount = accountService.saveAccountAndLink("Kitchen", address,
          "Main Kitchen", gsBuilding, atComponent, dcAccount, early1930)

      comment = new Comment(entityType: etAccount, entityId: kitAccount.id,
          user: user3, body: "dishwasher could use recycled water")
      comment.save()
      comment.dateCreated = new Date(110, 3, 14)
      comment.save()

      // bathroom
      Account bathAccount = accountService.saveAccountAndLink("Bathroom",address,
          "First Floor Bathroom", gsBuilding, atComponent, cvAccount, early1930)

      comment = new Comment(entityType: etAccount, entityId: bathAccount.id,
          user: user4, body: "Could install low-flow toilets")
      comment.save()
      comment.dateCreated = new Date(110, 7, 25)
      comment.save()

      // laundry
      Account laundryAccount = accountService.saveAccountAndLink("Laundry", address,
          "Main Laundry",gsBuilding, atComponent, cvAccount,early1930)
      comment = new Comment(entityType: etAccount, entityId: laundryAccount.id,
          user: user4, body: "Clothes washer could be upgraded")
      comment.save()
      comment.dateCreated = new Date(110, 3, 9)
      comment.save()
      comment = new Comment(entityType: etAccount, entityId: laundryAccount.id,
          user: user3, body: "Plumbing must be upgraded")
      comment.save()
      comment.dateCreated = new Date(110, 5, 17)
      comment.save()

      // landscaping
      Account landscapeAccount = accountService.saveAccountAndLink( "Landscaping",address,
          "Landscaping around the building",gsBuilding, atComponent, cvAccount, early1930)
      comment = new Comment(entityType: etAccount, entityId: landscapeAccount.id,
          user: user3, body: "Can optimize sprinkler cycles")
      comment.save()
      comment.dateCreated = new Date(110, 4, 4)
      comment.save()
      comment = new Comment(entityType: etAccount, entityId: landscapeAccount.id,
          user: user3, body: "Could use recycled water")
      comment.save()
      comment.dateCreated = new Date(110, 4, 6)
      comment.save()

      // sewer
      Account sewerAccount = accountService.saveAccountAndLink("Sewer", address,
          "Sewer", gsBuilding, atComponent, cvAccount, early1930)
      comment = new Comment(entityType: etAccount, entityId: sewerAccount.id,
          user: user3, body: "Flows to bay")
      comment.save()
      comment.dateCreated = new Date(110, 5, 16)
      comment.save()

      // recovery device
      Account rdAccount = accountService.saveAccountAndLink("RecoveryDev",address,
          "Water recovery unit", gsBuilding, atComponent, cvAccount, early1930)

      // Kitchen flows
      flowService.saveFlow(wslAccount, kitAccount, uomKiloGallon, resFreshwater, 40)
      flowService.saveFlow(kitAccount, sewerAccount, uomKiloGallon, resGraywater, 35)

      // Laundry flows
      flowService.saveFlow(wslAccount, laundryAccount, uomKiloGallon, resFreshwater, 30)
      flowService.saveFlow(laundryAccount, sewerAccount, uomKiloGallon, resGraywater, 25)


      // Landscaping flows
      flowService.saveFlow(wslAccount, landscapeAccount, uomKiloGallon, resFreshwater, 12)
      flowService.saveFlow(landscapeAccount, sewerAccount, uomKiloGallon, resGraywater, 6)

      // Recovery flows
      flowService.saveFlow( laundryAccount, rdAccount, uomKiloGallon, resGraywater, 15)
      flowService.saveFlow(rdAccount, landscapeAccount, uomKiloGallon, resGraywater, 15)

      // Company NextGen Gases and their buildings begins here

      address = new Address(
          addressLine1: "1515 Felipe Avenue", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95124",
          latitude: 37.336355, longitude: -121.852938)
      address.save()

      Account nxtGenAccount = accountService.saveAccountAndLink("NextGen Gases Company", address,
          "NextGen is a leading producer of industrial gases", gsCluster, atGroup, ncAccount, early1998)
      tagUsageService.addTag("Account", nxtGenAccount.id, "factory")
      tagUsageService.addTag("Account", nxtGenAccount.id, "industrial-gases")

      address = new Address(
          addressLine1: "1515 Felipe Avenue", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95124",
          latitude: 37.336455, longitude: -121.852956)
      address.save()
      Account building1 = accountService.saveAccountAndLink("NG Bldg-1", address,
          "Admin Building", gsCluster, atBuilding, nxtGenAccount,  early1998)
      tagUsageService.addTag("Account", building1.id, "energy")
      tagUsageService.addTag("Account", building1.id, "electricity")
      tagUsageService.addTag("Account", building1.id, "computers")
      tagUsageService.addTag("Account", building1.id, "printers")

      address = new Address(
          addressLine1: "1515 Felipe Avenue", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95124",
          latitude: 37.336455, longitude: -121.852956)
      address.save()
      Account building2 = accountService.saveAccountAndLink("NG Bldg-2", address,
          "Power Generator Building", gsCluster, atBuilding, nxtGenAccount, early2008)
      tagUsageService.addTag("Account", building2.id, "energy")
      tagUsageService.addTag("Account", building2.id, "electricity")
      tagUsageService.addTag("Account", building2.id, "cooling-tower")
    }
    else {
      println "Existing accounts, skipping creation."
    }

    if (Transaction.count() == 0) {
      println "Fresh Database. Creating transactions."

      // Build a few transactions
      TransactionType ttDirectProdW = TransactionType.get(1)
      TransactionType ttDirectConsW = TransactionType.get(2)
      TransactionType ttDirectProdE = TransactionType.get(3)
      TransactionType ttDirectConsE = TransactionType.get(4)
      TransactionType ttElectricity = TransactionType.get(5)

      Account nxtGenAccount = Account.findByName("NextGen Gases Company")
      Account building1 = Account.findByName("NG Bldg-1")
      Account building2 = Account.findByName("NG Bldg-2")
      Account acMainSupply = Account.findByName("Water Supply Line")
      Account acKitchen = Account.findByName("Kitchen")
      Account acBathroom = Account.findByName("Bathroom")
      Account acLaundry = Account.findByName("Laundry")
      Account acLandscaping = Account.findByName("Landscaping")

      Resource resElectricity = Resource.findByName("Electricity")
      Resource resNaturalGas = Resource.findByName("Natural Gas")
      Resource resGasoline = Resource.findByName("Gasoline")
      Resource resDiesel = Resource.findByName("Diesel")
      Resource resFreshwater = Resource.findByName("Freshwater")
      Resource resGraywater = Resource.findByName("Greywater")
      Resource resBlackwater = Resource.findByName("Blackwater")
      Resource resSolidWaste = Resource.findByName("Solid Waste")

      UnitOfMeasure uomKiloGallon = UnitOfMeasure.findByName("kgal")
      UnitOfMeasure uomKiloWattHour = UnitOfMeasure.findByName("kWh")
      UnitOfMeasure uomTherm =  UnitOfMeasure.findByName("therm")
      UnitOfMeasure uomMetricTon =  UnitOfMeasure.findByName("metric ton")

      Transaction transaction

      transaction = transactionService.saveTransaction(nxtGenAccount, ttDirectConsW, resFreshwater,
          uomKiloGallon, 400.0)
      transactionService.updateStartDate(transaction, new Date(110,9,12))

      transaction = transactionService.saveTransaction(nxtGenAccount, ttElectricity, resElectricity,
          uomKiloWattHour, 130.0)
      transactionService.updateStartDate(transaction, new Date(110,7,27))

      transaction = transactionService.saveTransaction(building1, ttDirectConsW, resFreshwater,
          uomKiloGallon, 200.0)
      transactionService.updateStartDate(transaction, new Date(110,7,9))

      transaction = transactionService.saveTransaction(building1, ttDirectConsE, resNaturalGas,
          uomKiloWattHour, 155.0)
      transactionService.updateStartDate(transaction, new Date(110,7,2))

      transaction = transactionService.saveTransaction(building1, ttDirectConsE, resNaturalGas,
          uomKiloWattHour, 351.0)
      transactionService.updateStartDate(transaction, new Date(110,8,12))

      transaction = transactionService.saveTransaction(building1, ttElectricity, resElectricity,
          uomKiloWattHour, 272.0)
      transactionService.updateStartDate(transaction, new Date(110,7,12))

      transaction = transactionService.saveTransaction(building2, ttDirectConsW, resFreshwater,
          uomKiloGallon, 432.0)
      transactionService.updateStartDate(transaction, new Date(110,7,12))

      transaction = transactionService.saveTransaction(building2, ttDirectConsW, resFreshwater,
          uomKiloGallon, 371.0)
      transactionService.updateStartDate(transaction, new Date(110,8,14))

      transaction = transactionService.saveTransaction(building2, ttElectricity, resElectricity,
          uomKiloWattHour, 230.0)
      transactionService.updateStartDate(transaction, new Date(110,7,17))

      transaction = transactionService.saveTransaction(acKitchen, ttDirectConsW, resFreshwater,
          uomKiloGallon, 71.0)
      transactionService.updateStartDate(transaction, new Date(110,6,14))

      transaction = transactionService.saveTransaction(acKitchen, ttElectricity, resElectricity,
          uomKiloWattHour, 30.0)
      transactionService.updateStartDate(transaction, new Date(110,6,17))

      transaction = transactionService.saveTransaction(acLaundry, ttDirectConsW, resFreshwater,
          uomKiloGallon, 53.0)
      transactionService.updateStartDate(transaction, new Date(110,7,4))

      transaction = transactionService.saveTransaction(acLaundry, ttElectricity, resElectricity,
          uomKiloWattHour, 23.3)
      transactionService.updateStartDate(transaction, new Date(110,7,7))

      transaction = transactionService.saveTransaction(acLaundry, ttDirectConsE, resNaturalGas,
          uomKiloWattHour, 22.9)
      transactionService.updateStartDate(transaction, new Date(110,7,7))
      transactionService.updateEndDate(transaction, new Date(110,8,12))

      transaction = transactionService.saveTransaction(acBathroom, ttDirectConsW, resFreshwater,
          uomKiloGallon, 32.2)
      transactionService.updateStartDate(transaction, new Date(110,7,13))
      transactionService.updateEndDate(transaction, new Date(110,8,17))

      transaction = transactionService.saveTransaction(acBathroom, ttElectricity, resElectricity,
          uomKiloWattHour, 12.6)
      transactionService.updateStartDate(transaction, new Date(110,7,17))
      transactionService.updateEndDate(transaction, new Date(110,8,16))

      transaction = transactionService.saveTransaction(acLandscaping, ttDirectConsW, resFreshwater,
          uomKiloGallon, 25.2)
      transactionService.updateStartDate(transaction, new Date(110,6,23))
      transactionService.updateEndDate(transaction, new Date(110,7,27))

      transaction = transactionService.saveTransaction(acLandscaping, ttElectricity, resElectricity,
          uomKiloWattHour, 17.6)
      transactionService.updateStartDate(transaction, new Date(110,6,27))
      transactionService.updateEndDate(transaction, new Date(110,7,26))
    }
    else {
      println "Existing transactions, skipping creation."
    }

    if (Project.count() == 0) {
      println "Fresh Database. Creating projects."

      // Build a few Projects, put comments on some
      Account nxtGenAccount = Account.findByName("NextGen Gases Company")
      Account building1 = Account.findByName("NG Bldg-1")
      Account building2 = Account.findByName("NG Bldg-2")
      Account acMainSupply = Account.findByName("Water Supply Line")
      Account acKitchen = Account.findByName("Kitchen")
      Account acBathroom = Account.findByName("Bathroom")
      Account acLaundry = Account.findByName("Laundry")
      Account acLandscaping = Account.findByName("Landscaping")

      ProjectStatus psPending = ProjectStatus.findBySequence(1)
      ProjectType ptRecycledWater = ProjectType.findByName("Recycled Water")
      Provider provSanJoseMuni = Provider.findByName("San Jose Municipal Water")
      Provider provSantaClara = Provider.findByName("City of Santa Clara - Water Service")
      PipelineFoot pipelineFoot20Paved = PipelineFoot.findBySeqNum(11)
      PipelineFoot pipelineFoot20Unpaved = PipelineFoot.findBySeqNum(12)
      Project project
      ROIProject roiProject

      project = projectService.saveProject(building2, ptRecycledWater, psPending, "Cooling Tower Retrofit", new Date(110,1,1),
          "Changeover on tower T-85", 2, 25000)
      tagUsageService.addTag("Project", project.id, "water")
      tagUsageService.addTag("Project", project.id, "recycled-water")

      roiProject =projectService.saveROIProject(project, provSanJoseMuni, provSanJoseMuni, 3740000, 10000)
      projectService.updateCycles(roiProject, 2.5, 3.5)
      projectService.updatePipelineFootAndDistance(roiProject, pipelineFoot20Paved, 10, "pipeline1")
      projectService.recalc(project, roiProject, null)
      project.save()

      project = projectService.saveProject(building1, ptRecycledWater, psPending, "Changeover on tower T-85", new Date(110,4,15),
          "Upgrade of generator G-34R", 3, 35000)
      tagUsageService.addTag("Project", project.id, "water")
      tagUsageService.addTag("Project", project.id, "energy")
      tagUsageService.addTag("Project", project.id, "recycled-water")

      roiProject =projectService.saveROIProject(project, provSantaClara, provSantaClara, 2000000, 15000)
      projectService.updateCycles(roiProject, 2.5, 3.75)
      projectService.updatePipelineFootAndDistance(roiProject, pipelineFoot20Paved, 10, "pipeline1")
      projectService.updatePipelineFootAndDistance(roiProject, pipelineFoot20Unpaved, 10, "pipeline2")

      projectService.recalc(project, roiProject, null)
      project.save()

    }
    else {
      println "Existing projects, skipping creation."
    }
  }

  void createCustomersIfRequired() {
    if (Account.findByName("Air Products - Santa Clara") == null) {
      println "Fresh database. Creating customers."

      AccountType atCustomer = AccountType.findByName("Customer")
      GeoScale gsRegion = GeoScale.findByName("Region")
      Address address
      Contact contact
      Account account

      address = new Address(addressLine1: "1515 Norman Avenue", city: "Santa Clara",
          stateCode: Address.STATES.getAt(6), postalCode: "95054-2065",
          latitude: 37.386355, longitude: -121.952938);
      address.save()
      contact = new Contact(contactName: "APCI contact", phoneNo: "408-988-2140")
      contact.save()
      account = new Account(type: atCustomer, name: "Air Products - Santa Clara", geoScale: gsRegion,
          description: "Industrial gas production facility",
          address: address, contact: contact)
      account.save();

      address = new Address(addressLine1: "200 E. Santa Clara Street", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95112",
          latitude: 37.33767, longitude: -121.885618);
      address.save()
      contact = new Contact(contactName: "Customer Service contact", phoneNo: "408-535-3500")
      contact.save()
      account = new Account(type: atCustomer, name: "San Jose City Hall", geoScale: gsRegion,
          description: "The city hall building",
          address: address, contact: contact)
      account.save();

      address = new Address(addressLine1: "1701 Airport Blvd", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95110",
          latitude: 37.362897, longitude: -121.928848);
      address.save()
      contact = new Contact(contactName: "Airport Contact", phoneNo: "408-555-1212", email: "jerry.mathew@ge.com")
      contact.save()
      account = new Account(type: atCustomer, name: "Mineta Airport", geoScale: gsRegion,
          description: "San Jose International Airport",
          address: address, contact: contact)
      account.save();

      address = new Address(addressLine1: "One Blanchard Road", addressLine2: "P.O. Box 13190", city: "San Jose",
          stateCode: Address.STATES.getAt(6), postalCode: "95037",
          latitude: 37.2206, longitude: -121.7457);
      address.save()
      contact = new Contact(contactName: "Public Affiairs Office", phoneNo: "408-555-1212")
      contact.save()
      account = new Account(type: atCustomer, name: "Metcalf Energy Center", geoScale: gsRegion,
          description: "600-megawatt power generation facility built by Calpine Corporation",
          address: address, contact: contact)
      account.save();
    }
    else {
      println "Existing customers, skipping creation"
    }
  }

  void createComponentsIfRequired() {
    if (Module.count() == 0) {
      println "Fresh Database. Creating modules."

      Module module

      module = new Module(name: "Dashboard");
      module.save()
      module = new Module(name: "Recycled Water");
      module.save()
      module = new Module(name: "Urban Water Cycle");
      module.save()
      module = new Module(name: "Adminstration");
      module.save()
    }
    else {
      println "Existing modules, skipping creation."
    }

    if (MenuItem.count() == 0) {
      println "Fresh Database. Creating menu items."

      MenuItem menuItem

      menuItem = new MenuItem(seqNum:  20, name: "Dashboard", controller: 'summary');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  30, name: "Facilities", controller: 'account');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  40, name: "Map", controller: 'geomap');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  50, name: "Balances", controller: 'balance');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  60, name: "Preferences", controller: 'preferences');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  80, name: "Projects", controller: 'project');
      menuItem.save()
      menuItem = new MenuItem(seqNum: 100, name: "Administration", controller: 'administration', administrative: true);
      menuItem.save()
      menuItem = new MenuItem(seqNum: 110, name: "Help", controller: 'help');
      menuItem.save()
    }
    else {
      println "Existing menu items, skipping creation."
    }
  }
}
