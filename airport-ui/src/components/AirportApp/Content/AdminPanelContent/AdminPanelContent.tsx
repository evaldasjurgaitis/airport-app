import React from 'react';

import AirportDataImport from './DataImports/AirportDataImport';
import CountryDataImport from './DataImports/CountryDataImport';
import RegionDataImport from './DataImports/RegionDataImport';

import './AdminPanelContent.scss';

const AdminPanelContent = () => {
    return (
        <div className="admin-panel-content">
            <h1>Admin panel</h1>
            <CountryDataImport />
            <RegionDataImport />
            <AirportDataImport />
        </div>
    );
};

export default AdminPanelContent;
