import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('CsytOneBss e2e test', () => {
  const csytOneBssPageUrl = '/csyt-one-bss';
  const csytOneBssPageUrlPattern = new RegExp('/csyt-one-bss(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const csytOneBssSample = {
    maCsyt: 'hate',
    maThuebao: 'criminalise',
    maDuan: 'devil unhitch',
    trangthai: 26704,
    thoigianCapnhat: '2024-07-02',
  };

  let csytOneBss;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/csyt-one-bsses+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/csyt-one-bsses').as('postEntityRequest');
    cy.intercept('DELETE', '/api/csyt-one-bsses/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (csytOneBss) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/csyt-one-bsses/${csytOneBss.id}`,
      }).then(() => {
        csytOneBss = undefined;
      });
    }
  });

  it('CsytOneBsses menu should load CsytOneBsses page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('csyt-one-bss');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('CsytOneBss').should('exist');
    cy.url().should('match', csytOneBssPageUrlPattern);
  });

  describe('CsytOneBss page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(csytOneBssPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create CsytOneBss page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/csyt-one-bss/new$'));
        cy.getEntityCreateUpdateHeading('CsytOneBss');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', csytOneBssPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/csyt-one-bsses',
          body: csytOneBssSample,
        }).then(({ body }) => {
          csytOneBss = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/csyt-one-bsses+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [csytOneBss],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(csytOneBssPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details CsytOneBss page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('csytOneBss');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', csytOneBssPageUrlPattern);
      });

      it('edit button click should load edit CsytOneBss page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('CsytOneBss');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', csytOneBssPageUrlPattern);
      });

      it('edit button click should load edit CsytOneBss page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('CsytOneBss');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', csytOneBssPageUrlPattern);
      });

      it('last delete button click should delete instance of CsytOneBss', () => {
        cy.intercept('GET', '/api/csyt-one-bsses/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('csytOneBss').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', csytOneBssPageUrlPattern);

        csytOneBss = undefined;
      });
    });
  });

  describe('new CsytOneBss page', () => {
    beforeEach(() => {
      cy.visit(`${csytOneBssPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('CsytOneBss');
    });

    it('should create an instance of CsytOneBss', () => {
      cy.get(`[data-cy="maCsyt"]`).type('serene');
      cy.get(`[data-cy="maCsyt"]`).should('have.value', 'serene');

      cy.get(`[data-cy="maThuebao"]`).type('oof till bouncy');
      cy.get(`[data-cy="maThuebao"]`).should('have.value', 'oof till bouncy');

      cy.get(`[data-cy="maDuan"]`).type('provided');
      cy.get(`[data-cy="maDuan"]`).should('have.value', 'provided');

      cy.get(`[data-cy="trangthai"]`).type('22976');
      cy.get(`[data-cy="trangthai"]`).should('have.value', '22976');

      cy.get(`[data-cy="thoigianCapnhat"]`).type('2024-07-02');
      cy.get(`[data-cy="thoigianCapnhat"]`).blur();
      cy.get(`[data-cy="thoigianCapnhat"]`).should('have.value', '2024-07-02');

      cy.get(`[data-cy="maTinh"]`).type('next');
      cy.get(`[data-cy="maTinh"]`).should('have.value', 'next');

      cy.get(`[data-cy="maSpdv"]`).type('yuck');
      cy.get(`[data-cy="maSpdv"]`).should('have.value', 'yuck');

      cy.get(`[data-cy="maHopdong"]`).type('and mmm tall');
      cy.get(`[data-cy="maHopdong"]`).should('have.value', 'and mmm tall');

      cy.get(`[data-cy="ngaykyHopdong"]`).type('2024-07-02');
      cy.get(`[data-cy="ngaykyHopdong"]`).blur();
      cy.get(`[data-cy="ngaykyHopdong"]`).should('have.value', '2024-07-02');

      cy.get(`[data-cy="hieulucTu"]`).type('2024-07-02');
      cy.get(`[data-cy="hieulucTu"]`).blur();
      cy.get(`[data-cy="hieulucTu"]`).should('have.value', '2024-07-02');

      cy.get(`[data-cy="hieulucDen"]`).type('2024-07-02');
      cy.get(`[data-cy="hieulucDen"]`).blur();
      cy.get(`[data-cy="hieulucDen"]`).should('have.value', '2024-07-02');

      cy.get(`[data-cy="tenKhachhang"]`).type('after phooey');
      cy.get(`[data-cy="tenKhachhang"]`).should('have.value', 'after phooey');

      cy.get(`[data-cy="diachiKhachhang"]`).type('hmph outfit upbeat');
      cy.get(`[data-cy="diachiKhachhang"]`).should('have.value', 'hmph outfit upbeat');

      cy.get(`[data-cy="mota"]`).type('centimeter');
      cy.get(`[data-cy="mota"]`).should('have.value', 'centimeter');

      cy.get(`[data-cy="lastUser"]`).type('brink brr');
      cy.get(`[data-cy="lastUser"]`).should('have.value', 'brink brr');

      cy.get(`[data-cy="lastModified"]`).type('2024-07-02T22:08');
      cy.get(`[data-cy="lastModified"]`).blur();
      cy.get(`[data-cy="lastModified"]`).should('have.value', '2024-07-02T22:08');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        csytOneBss = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', csytOneBssPageUrlPattern);
    });
  });
});
