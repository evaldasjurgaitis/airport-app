import React, { useCallback, useContext, useEffect, useState } from 'react';
import queryString from 'query-string';

import api from '../../../../../utils/api';

import Table from '../../../../shared/Table/Table';
import AirportInfoModal from '../AirportInfoModal/AirportInfoModal';
import getAirportTableColumns from './getAirportTableColumns';

import { AirportDetail, Pagination } from '../../../typing';

import { AirportContext } from '../../../Contexts/AirportContext';

import './AirportTable.scss';

function getUrl(selectedCountry?: string, selectedRegion?: string, pagination?: Pagination) {
    return queryString.stringifyUrl({
        url: 'http://localhost:8081/api/airports',
        query: {
            isoCountry: selectedCountry,
            isoRegion: selectedRegion,
            page: pagination ? pagination.current - 1 : 0,
            size: pagination ? pagination.pageSize : 10,
        },
    });
}

const AirportTable = () => {
    const { selectedCountry, selectedRegion } = useContext(AirportContext);

    const [loading, setLoading] = useState<boolean>(false);
    const [airports, setAirports] = useState<AirportDetail[]>([]);
    const [pagination, setPagination] = useState<Pagination>({
        current: 1,
        pageSize: 10,
        total: 0,
    });
    const [showAirportInfo, setShowAirportInfo] = useState(false);
    const [selectedAirportId, setSelectedAirportId] = useState<number | null>(null);

    const fetchAirports = useCallback(
        (pagination?: Pagination) => {
            setLoading(true);
            api()
                .get(getUrl(selectedCountry, selectedRegion, pagination))
                .then((response) => {
                    setLoading(false);
                    setPagination({
                        current: response.data.number + 1,
                        pageSize: response.data.size,
                        total: response.data.totalElements,
                    });
                    setAirports(
                        response.data.content.map((airport: AirportDetail) => ({
                            id: airport.id,
                            name: airport.name,
                            municipality: airport.municipality,
                            providerDetail: airport.providerDetail,
                        }))
                    );
                });
        },
        [selectedCountry, selectedRegion]
    );

    useEffect(() => {
        if (selectedCountry) {
            fetchAirports();
        }
    }, [selectedCountry, selectedRegion, fetchAirports]);

    const handleTableChange = (pagination: Pagination) => {
        fetchAirports(pagination);
    };

    return (
        <div className="airport-table">
            <AirportInfoModal
                airportId={selectedAirportId}
                visible={showAirportInfo}
                onClose={() => setShowAirportInfo(false)}
            />
            <Table
                columns={getAirportTableColumns}
                rows={airports}
                pagination={pagination}
                loading={loading}
                onChange={handleTableChange}
                onRowClick={(airportId) => {
                    setSelectedAirportId(airportId);
                    setShowAirportInfo(true);
                }}
            />
        </div>
    );
};

export default AirportTable;
