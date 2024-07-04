import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CsytOneBss from './csyt-one-bss';
import CsytOneBssDetail from './csyt-one-bss-detail';
import CsytOneBssUpdate from './csyt-one-bss-update';
import CsytOneBssDeleteDialog from './csyt-one-bss-delete-dialog';

const CsytOneBssRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CsytOneBss />} />
    <Route path="new" element={<CsytOneBssUpdate />} />
    <Route path=":id">
      <Route index element={<CsytOneBssDetail />} />
      <Route path="edit" element={<CsytOneBssUpdate />} />
      <Route path="delete" element={<CsytOneBssDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CsytOneBssRoutes;
