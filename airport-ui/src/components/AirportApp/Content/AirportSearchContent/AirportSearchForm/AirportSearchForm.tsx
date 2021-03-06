import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import axios from 'axios';
import queryString from 'query-string';

import Row from '../../../../shared/Row/Row';
import Col from '../../../../shared/Col/Col';
import Select, { Option } from '../../../../shared/Select/Select';

import { CountryDetail, RegionDetail } from '../../../typing';

import './AirportSearchForm.scss';

interface Props {
    selectedCountry: string | undefined;
    setSelectedCountry: Dispatch<SetStateAction<string | undefined>>;
    selectedRegion: string | undefined;
    setSelectedRegion: Dispatch<SetStateAction<string | undefined>>;
}

function getUrl(selectedCountry: string) {
    return queryString.stringifyUrl({
        url: 'http://localhost:8081/api/regions',
        query: {
            isoCountry: selectedCountry,
        },
    });
}

const AirportSearchForm = ({ selectedCountry, setSelectedCountry, selectedRegion, setSelectedRegion }: Props) => {
    const [countries, setCountries] = useState<Option[]>([]);
    const [regions, setRegions] = useState<Option[]>([]);

    useEffect(() => {
        axios.get('http://localhost:8081/api/countries').then((response) => {
            setCountries(
                response.data.map((country: CountryDetail) => ({
                    value: country.code,
                    label: country.name,
                }))
            );
        });
    }, [setCountries]);

    const onCountryChange = (selectedCountry: string) => {
        setSelectedRegion(undefined);
        setSelectedCountry(selectedCountry);
        axios.get(getUrl(selectedCountry)).then((response) => {
            setRegions(
                response.data.map((region: RegionDetail) => ({
                    value: region.code,
                    label: region.name,
                }))
            );
        });
    };

    return (
        <div className="airport-search-form">
            <h1>Airport search panel</h1>
            <Row gutter={[16, { xs: 8, sm: 16, md: 24, lg: 32 }]}>
                <Col span={6} xs={24} sm={8} md={8} xl={4}>
                    <Select
                        value={selectedCountry}
                        showSearch
                        options={countries}
                        onChange={onCountryChange}
                        placeholder="Select country"
                    />
                </Col>
                <Col span={6} xs={24} sm={8} md={8} xl={4}>
                    <Select
                        value={selectedRegion}
                        disabled={!selectedCountry}
                        showSearch
                        options={regions}
                        onChange={(selectedRegion) => {
                            setSelectedRegion(selectedRegion);
                        }}
                        placeholder="Select region"
                    />
                </Col>
            </Row>
        </div>
    );
};

export default AirportSearchForm;
