import React, { useEffect, useState } from 'react';
import axios from 'axios';
import queryString from 'query-string';

import Table from '../../../../shared/Table/Table';

import { ProviderDetail } from '../../../typing';

import './AirportTable.scss';

interface Props {
    selectedCountry: string | undefined;
    selectedRegion?: string | undefined;
}

const AirportTable = ({ selectedCountry, selectedRegion }: Props) => {
    const [loading, setLoading] = useState<boolean>(false);
    const [airports, setAirports] = useState([]);
    const [pagination, setPagination] = useState({
        current: 1,
        pageSize: 10,
        total: 0,
    });

    function getUrl(selectedCountry?: string, selectedRegion?: string, pagination?: any) {
        return queryString.stringifyUrl({
            url: 'http://localhost:8081/api/airports',
            query: {
                isoCountry: selectedCountry,
                isoRegion: selectedRegion,
                page: pagination.current - 1,
                size: pagination.pageSize,
            },
        });
    }

    useEffect(() => {
        if (selectedCountry) {
            setLoading(true);
            axios.get(getUrl(selectedCountry, selectedRegion, { pageSize: 10, current: 1 })).then((response) => {
                setLoading(false);
                setPagination({
                    current: response.data.number + 1,
                    pageSize: response.data.size,
                    total: response.data.totalElements,
                });
                setAirports(
                    response.data.content.map(
                        (airport: { name: string; municipality: string; providerDetail: ProviderDetail }) => ({
                            name: airport.name,
                            municipality: airport.municipality,
                            providerDetail: airport.providerDetail,
                        })
                    )
                );
            });
        }
    }, [selectedCountry, selectedRegion]);

    const columns = [
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: 'Municipality',
            dataIndex: 'municipality',
            key: 'municipality',
        },
        {
            title: 'Price',
            dataIndex: 'providerDetail',
            key: 'providerDetail',
            render: (providerDetail: ProviderDetail) => `${providerDetail ? providerDetail.price : 'N/A'}`,
        },
    ];

    const handleTableChange = (pagination: any) => {
        setLoading(true);
        axios.get(getUrl(selectedCountry, selectedRegion, pagination)).then((response) => {
            setLoading(false);
            setPagination({
                current: response.data.number + 1,
                pageSize: response.data.size,
                total: response.data.totalElements,
            });
            setAirports(
                response.data.content.map(
                    (airport: { name: string; municipality: string; providerDetail: ProviderDetail }) => ({
                        name: airport.name,
                        municipality: airport.municipality,
                        providerDetail: airport.providerDetail,
                    })
                )
            );
        });
    };

    return (
        <div className="airport-table">
            <Table
                columns={columns}
                rows={airports}
                pagination={pagination}
                loading={loading}
                onChange={handleTableChange}
            />
        </div>
    );
};

export default AirportTable;
